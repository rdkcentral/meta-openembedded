# This file is Confidential Information of CUJO LLC.
# Copyright (c) 2018-2019 CUJO LLC. All rights reserved.

SUMMARY = "CUJO Lua OOP library"
SECTION = "cujo/share/lua"
DEPENDS = "lua"
RDEPENDS_${PN} += "lua (>= 5.3)"
SRC_URI = "git://github.com/getCUJO/loop.git;tag=v3.0.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=194edb09293c21087424cccae2125efd"
S = "${WORKDIR}/git"

LUA_LIB_DIR =  "${libdir}/lua/5.3"
LUA_SHARE_DIR = "${datadir}/lua/5.3"

do_install() {
	install -d ${D}${LUA_SHARE_DIR}
	install -d ${D}${LUA_SHARE_DIR}/loop
	install -d ${D}${LUA_SHARE_DIR}/loop/scoped
	install -m 0755 ${S}/lua/*.lua ${D}${LUA_SHARE_DIR}
	install -m 0755 ${S}/lua/loop/*.lua ${D}${LUA_SHARE_DIR}/loop
	install -m 0755 ${S}/lua/loop/scoped/*.lua ${D}${LUA_SHARE_DIR}/loop/scoped
}

FILES_${PN} += "${LUA_SHARE_DIR}/*"
