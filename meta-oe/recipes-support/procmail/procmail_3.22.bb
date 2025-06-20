SUMMARY = "Mail processing program"
DESCRIPTION = "Procmail can be used to create mail-servers, mailing lists, sort your\
incoming mail into separate folders/files (real convenient when subscribing\
to one or more mailing lists or for prioritising your mail), preprocess\
your mail, start any programs upon mail arrival (e.g. to generate different\
chimes on your workstation for different types of mail) or selectively\
forward certain incoming mail automatically to someone."
HOMEPAGE = "http://www.procmail.org/"
SECTION = "Applications/System"

SRC_URI = "http://www.ring.gr.jp/archives/net/mail/${BPN}/${BP}.tar.gz \
    file://from-debian-to-fix-compile-errors.patch \
    file://from-debian-to-modify-parameters.patch \
    file://from-debian-to-fix-man-file.patch \
    file://man-file-mailstat.1-from-debian.patch \
    file://CVE-2014-3618.patch \
    file://CVE-2017-16844.patch \
    file://gcc14.patch \
    file://0001-fix-uname-declaration.patch \
    file://0001-fix-too-many-arguments-issue.patch \
"
SRC_URI[sha256sum] = "087c75b34dd33d8b9df5afe9e42801c9395f4bf373a784d9bc97153b0062e117"

LICENSE = "GPL-2.0-only & Artistic-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=a71e50e197a992c862379e576e669757 \
    file://Artistic;md5=505e00d03c3428cde21b17b2a386590e"

DEPENDS = "libnet"
inherit autotools-brokensep

do_configure() {
    find examples -type f | xargs chmod 644
    export CC="${BUILD_CC}"
    export LD="${BUILD_LD}"
    export CFLAGS="${BUILD_CFLAGS}"
    export AR="${BUILD_AR}"
    export AS="${BUILD_AS}"
    make TARGET_CFLAGS="$TARGET_CFLAGS -D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64" LDFLAGS0="${BUILD_LDFLAGS}" autoconf.h
}

do_compile() {
    oe_runmake -i CFLAGS="$TARGET_CFLAGS -Wno-comments -Wno-implicit-int -Wno-implicit-function-declaration -Wno-incompatible-pointer-types -D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64" LDFLAGS0="${LDFLAGS}"
}

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${mandir}/man1
    install -d ${D}${mandir}/man5
    oe_runmake -i BASENAME=${D}/usr MANDIR=${D}${mandir} install
    install -m 0644 debian/mailstat.1 ${D}${mandir}/man1
}

CVE_STATUS[CVE-1999-0475] = "fixed-version: No action required. The current version (3.22) is not affected by the CVE."
