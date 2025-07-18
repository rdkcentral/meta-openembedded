SUMMARY = "Forward a serial using TCP/IP"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=ebb5c50ab7cab4baeffba14977030c07"
SECTION = "console/devel"
SRCREV = "07c6fdede0870edc37a8d51d033b6e7e29aa7c91"
PV = "1.1+git"

SRC_URI = "git://github.com/freesmartphone/cornucopia.git;branch=master;protocol=https \
           file://0001-serial_forward-Disable-default-static-linking.patch;striplevel=3 \
           file://0001-correct-the-typo-in-include-file-name-string.h.patch;striplevel=3 \
          "
S = "${UNPACKDIR}/${BP}/tools/serial_forward"

inherit autotools
