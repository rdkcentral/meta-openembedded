# This file is Confidential Information of CUJO LLC.
# Copyright (c) 2018-2019 CUJO LLC. All rights reserved.

SUMMARY = "Lua Multipart Post 1.1"
SECTION = "cujo/lib/lua"
LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d0534bd9852e57976977cd6ff32a1177"
DEPENDS = "lua luasocket"
RDEPENDS_${PN} += "lua (>= 5.3) luasocket (>= 3.1.1)"
SRC_URI = "git://github.com/getCUJO/lua-multipart-post.git"
SRCREV = "808f9ca418753ca3a2a349389e12ee1fd24c0dcb"
S = "${WORKDIR}/git"

LUA_LIB_DIR =  "${libdir}/lua/5.3"
LUA_SHARE_DIR = "${datadir}/lua/5.3"

do_compile[noexec] = "1"

do_install() {
    install -d ${D}${LUA_SHARE_DIR}
    install -m 644 ${S}/*.lua ${D}${LUA_SHARE_DIR}
}

FILES_${PN} += "${LUA_SHARE_DIR}/*.lua"
