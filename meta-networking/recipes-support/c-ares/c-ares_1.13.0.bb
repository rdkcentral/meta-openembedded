# Copyright (c) 2012-2014 LG Electronics, Inc.

DESCRIPTION = "c-ares is a C library that resolves names asynchronously."
HOMEPAGE = "http://daniel.haxx.se/projects/c-ares/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=f4b026880834eb01c035c5e5cb47ccac"
SRCREV = "3be1924221e1326df520f8498d704a5c4c8d0cce"
PV = "1.13.0+gitr${SRCPV}"

SRC_URI = "git://github.com/c-ares/c-ares.git \
           file://0001-Added-support-libcares.pc-generation.patch \
          " 

S = "${WORKDIR}/git"
PR = "r1"

inherit autotools
inherit pkgconfig
inherit cmake

EXTRA_OECONF = "--enable-shared"
EXTRA_OECMAKE = " \
  -DCMAKE_INSTALL_BINDIR:PATH=${@os.path.relpath(d.getVar('bindir', True), d.getVar('prefix', True))} \
  -DCMAKE_INSTALL_SBINDIR:PATH=${@os.path.relpath(d.getVar('sbindir', True), d.getVar('prefix', True))} \
  -DCMAKE_INSTALL_LIBEXECDIR:PATH=${@os.path.relpath(d.getVar('libexecdir', True), d.getVar('prefix', True))} \
  -DCMAKE_INSTALL_SHAREDSTATEDIR:PATH=${@os.path.relpath(d.getVar('sharedstatedir', True), d.  getVar('prefix', True))} \
  -DCMAKE_INSTALL_LIBDIR:PATH=${@os.path.relpath(d.getVar('libdir', True), d.getVar('prefix', True))} \
  -DCMAKE_INSTALL_INCLUDEDIR:PATH=${@os.path.relpath(d.getVar('includedir', True), d.getVar('prefix', True))} \
  -DCMAKE_INSTALL_DATAROOTDIR:PATH=${@os.path.relpath(d.getVar('datadir', True), d.getVar('prefix', True))} \
  -DCMAKE_INSTALL_SO_NO_EXE=0 \
"

do_install_append() {
    install -d ${D}/${includedir}/ares
    install -m 0644 ares*.h ${D}/${includedir}/ares/
}

FILES_${PN}-dev += "${libdir}/cmake"
FILES_${PN}-utils = "${bindir}"

BBCLASSEXTEND = "native"
