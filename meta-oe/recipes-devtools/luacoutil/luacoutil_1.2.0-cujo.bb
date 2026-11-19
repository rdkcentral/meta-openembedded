# This file is Confidential Information of CUJO LLC.
# Copyright (c) 2018-2019 CUJO LLC. All rights reserved.

SUMMARY = "CUJO lua coutil"
SECTION = "cujo/share/lua"
DEPENDS = "luavararg"
RDEPENDS_${PN} += "luavararg (>= 2.1.0)"
SRC_URI = "git://github.com/getCUJO/coutil.git;tag=v1.2.0-cujo;nobranch=1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98ed66b5d87bb4152d51cba938287360"
S = "${WORKDIR}/git"

LUA_LIB_DIR =  "${libdir}/lua/5.3"
LUA_SHARE_DIR = "${datadir}/lua/5.3"

do_install() {
	install -d ${D}${LUA_SHARE_DIR}
	install -d ${D}${LUA_SHARE_DIR}/coutil
	install -d ${D}${LUA_SHARE_DIR}/coutil/socket
	install -d ${D}${LUA_SHARE_DIR}/coutil/time
	install -m 0755 ${S}/lua/coutil/*.lua ${D}${LUA_SHARE_DIR}/coutil
	install -m 0755 ${S}/lua/coutil/socket/*.lua ${D}${LUA_SHARE_DIR}/coutil/socket
	install -m 0755 ${S}/lua/coutil/time/*.lua ${D}${LUA_SHARE_DIR}/coutil/time
}

FILES_${PN} += "${LUA_SHARE_DIR}/*"
