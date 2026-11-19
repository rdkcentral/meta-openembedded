# This file is Confidential Information of CUJO LLC.
# Copyright (c) 2018-2019 CUJO LLC. All rights reserved.

SUMMARY = "luavararg 2.1.0"
SECTION = "cujo/lib/lua"
DEPENDS = "lua"
RDEPENDS_${PN} += "lua (>= 5.3)"
SRC_URI = "git://github.com/getCUJO/luavararg.git"
SRCREV = "0e5d7adba6e78f706bdddd8a76f8fb1ca79d6cad"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a0faecd3da9190bb6e1ec4c43dd0a6cb"
S = "${WORKDIR}/git"

LUA_LIB_DIR =  "${libdir}/lua/5.3"
LUA_SHARE_DIR = "${datadir}/lua/5.3"

CFLAGS_prepend = " -std=gnu99 -D_GNU_SOURCE -DLUA_USE_LINUX -DCUJO \
	-I${S} "
LDFLAGS_append = " -fPIC "

do_compile() {
	${CC} -shared -MD -DPIC ${LDFLAGS} ${CFLAGS} ${S}/src/vararg.c \
		-o libluavararg.so -ldl -lrt -llua
}

do_install() {
	install -d ${D}${LUA_LIB_DIR}
	install -m 0755 libluavararg.so ${D}${LUA_LIB_DIR}/vararg.so
}

FILES_${PN} += "${LUA_LIB_DIR}/vararg.so"
FILES_${PN}-dbg += "${LUA_LIB_DIR}/.debug"
