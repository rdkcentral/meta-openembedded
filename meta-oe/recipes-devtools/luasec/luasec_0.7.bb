# This file is Confidential Information of CUJO LLC.
# Copyright (c) 2018-2019 CUJO LLC. All rights reserved.

SUMMARY = "LuaSec 0.7"
SECTION = "cujo/lib/lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "openssl lua luasocket"
RDEPENDS_${PN} += "lua (>= 5.3) luasocket"
SRC_URI = "git://github.com/getCUJO/libluasec.git;tag=luasec-0.7"
FILESEXTRAPATHS_prepend:= "${THISDIR}/files:"
SRC_URI += "file://Makefile.patch"
S = "${WORKDIR}/git"

LUA_LIB_DIR =  "${libdir}/lua/5.3"
LUA_SHARE_DIR = "${datadir}/lua/5.3"
LIBNAME = "luasec.so.0.0.7"
CFLAGS_append = " -fPIC -std=gnu99 -I${S} -I${S}/src"
LDFLAGS_append = " -fPIC -ldl -lrt -Wl,-soname,luasec.so.0"

EXTRA_OEMAKE = " \
	INC_PATH=-I${STAGING_INCDIR} \
	'LD=${CC}' CMOD=${LIBNAME} LIB_PATH='-L${STAGING_LIBDIR}' \
	LUAPATH=${LUA_SHARE_DIR} \
	LUACPATH=${LUA_LIB_DIR} \
"

do_compile () {
	oe_runmake linux
}

do_install () {
	oe_runmake install DESTDIR=${D}
}

FILES_${PN} += " \
	${LUA_LIB_DIR}/*.so* \
	${LUA_SHARE_DIR}/* \
"
FILES_${PN}-dbg += " \
	${LUA_LIB_DIR}/.debug/*.so* \
	${LUA_SHARE_DIR}/.debug/* \
"

INSANE_SKIP_${PN} = "dev-so"
FILES_SOLIBSDEV=""
SOLIBS=".so"
