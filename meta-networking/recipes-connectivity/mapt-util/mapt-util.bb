SUMMARY = "Mapt Ivictl Utility"

LICENSE = "CLOSED"

#SRC_URI = "file://map_modules.tar.gz"
SRCREV="03d2baf2242ede7ce473039ac08026aa3c99f05b"
SRC_URI ="git://github.com/cernet/MAP.git;protocol=http"

PV = "1"

S = "${WORKDIR}/git/utils"

do_install() {
	install -d ${D}/${bindir}/
	cp ${S}/ivictl ${D}/${bindir}
}

#inherit native
#PACKAGES += "${PN}-ccsp"
#
#FILES_${PN}-ccsp = " \
#    /usr/ccsp/ivi/* \
#    "  
#FILES_${PN}-dbg = " \
#   ${prefix}/ccsp/ivi/.debug \
#    "
#FILES_${PN} += " \
#    ${prefix}/ccsp/ivi/ivictl \
#"
