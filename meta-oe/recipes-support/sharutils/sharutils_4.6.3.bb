SUMMARY = "This is the set of GNU shar utilities."
HOMEPAGE = "http://www.gnu.org/software/sharutils/"
SECTION = "console/utils"
LICENSE="GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit gettext autotools

SRC_URI = "ftp://ftp.gnu.org/gnu/${BPN}/REL-4.6.3/${BP}.tar.gz \
"

SRC_URI[md5sum] = "74127a560e59be6dfa8b59993eb0ca91"
SRC_URI[sha256sum] = "50b0c2a98a8f380aebd364f5e3f724d26f98f0e28d7f0796f09102f5eb5d5cc3"

do_install_append() {
    if [ -e ${D}${libdir}/charset.alias ]
    then
        rm -rf ${D}${libdir}/charset.alias
        rmdir --ignore-fail-on-non-empty ${D}${libdir}
    fi
}

BBCLASSEXTEND = "native"
