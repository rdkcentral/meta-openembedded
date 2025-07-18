DESCRIPTION = "InfluxDB is a time series database designed to handle high write and query loads."
HOMEPAGE = "https://www.influxdata.com/products/influxdb-overview/"

LICENSE = "MIT & ${GO_MOD_LICENSES}"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=f39a8d10930fb37bd59adabb3b9d0bd6"
require ${BPN}-licenses.inc

SRC_URI = "\
    git://${GO_IMPORT};protocol=https;branch=1.8;destsuffix=${GO_SRCURI_DESTSUFFIX} \
    file://0001-Use-v2.1.2-xxhash-to-fix-build-with-go-1.17.patch;patchdir=src/${GO_IMPORT} \
    file://0001-patch-term-module-for-mips-ispeed-ospeed-termios-abs.patch;patchdir=src/${GO_IMPORT} \
    file://influxdb \
    file://influxdb.conf \
"
require ${BPN}-go-mods.inc

SRCREV = "688e697c51fd5353725da078555adbeff0363d01"

GO_IMPORT = "github.com/influxdata/influxdb"
GO_INSTALL = "\
    ${GO_IMPORT}/cmd/influx \
    ${GO_IMPORT}/cmd/influxd \
"

inherit go-mod pkgconfig systemd update-rc.d useradd

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "influxdb"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_SERVICE:${PN} = "influxdb.service"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system -d /var/lib/influxdb -m -s /bin/nologin influxdb"

do_install:prepend() {
    test -e ${B}/src/${GO_IMPORT}/build.py && rm ${B}/src/${GO_IMPORT}/build.py
    test -e ${B}/src/${GO_IMPORT}/build.sh && rm ${B}/src/${GO_IMPORT}/build.sh
    rm -rf ${B}/src/${GO_IMPORT}/Dockerfile*

    sed -i -e "s#usr/bin/sh#bin/sh#g" ${B}/src/${GO_IMPORT}/scripts/ci/run_perftest.sh
}

do_install:append() {
    install -d ${D}${sysconfdir}/influxdb
    install -m 0644 ${UNPACKDIR}/influxdb.conf ${D}${sysconfdir}/influxdb
    chown -R root:influxdb ${D}${sysconfdir}/influxdb

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${UNPACKDIR}/influxdb ${D}${sysconfdir}/init.d/influxdb

    if [ "${@bb.utils.filter('DISTRO_FEATURES', 'sysvinit', d)}" ] ; then
        install -d ${D}${sysconfdir}/logrotate.d
        install -m 0644 ${S}/src/${GO_IMPORT}/scripts/logrotate ${D}${sysconfdir}/logrotate.d/influxdb
    fi

    if [ "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}" ] ; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${S}/src/${GO_IMPORT}/scripts/influxdb.service ${D}${systemd_system_unitdir}/influxdb.service
        install -d ${D}${libdir}/influxdb/scripts
        install -m 0755 ${S}/src/${GO_IMPORT}/scripts/influxd-systemd-start.sh ${D}${libdir}/influxdb/scripts/influxd-systemd-start.sh
    fi

    # TODO chown
}

FILES:${PN} += "${libdir}/influxdb/scripts/influxd-systemd-start.sh"

RDEPENDS:${PN} = "bash"
RDEPENDS:${PN}-dev = "bash"

CVE_STATUS[CVE-2019-10329] = "cpe-incorrect: Version does not match and only the Jenkins plugin is affected."
