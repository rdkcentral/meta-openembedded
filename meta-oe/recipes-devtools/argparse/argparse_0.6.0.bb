# This file is Confidential Information of CUJO LLC.
# Copyright (c) 2018-2019 CUJO LLC. All rights reserved.

SUMMARY = "A feature-rich command line parser for Lua inspired by argparse for Python."
SECTION = "cujo/share/lua"
DEPENDS = "lua"
RDEPENDS_${PN} += "lua (>= 5.3)"
SRC_URI = "git://github.com/getCUJO/argparse.git"
SRCREV = "412e6aca393e365f92c0315dfe50181b193f1ace"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=910fce0e89e2555047e2be1388279ece"
S = "${WORKDIR}/git"

LUA_LIB_DIR =  "${libdir}/lua/5.3"
LUA_SHARE_DIR = "${datadir}/lua/5.3"

do_install() {
	install -d ${D}${LUA_SHARE_DIR}
	install -m 0755 ${S}/src/argparse.lua ${D}${LUA_SHARE_DIR}
}

FILES_${PN} += "${LUA_SHARE_DIR}/argparse.lua"
