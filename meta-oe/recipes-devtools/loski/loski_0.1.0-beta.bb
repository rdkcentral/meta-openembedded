# This file is Confidential Information of CUJO LLC.
# Copyright (c) 2018-2019 CUJO LLC. All rights reserved.

SUMMARY = "loski 0.1.0-beta"

require loski.inc

do_compile() {
	oe_runmake linux ALL="process.so time.so"
}

do_install() {
	install -d ${D}${LUA_LIB_DIR}
	install -m 0755 src/process.so ${D}${LUA_LIB_DIR}/process.so
	install -m 0755 src/libluaosirt.so ${D}${LUA_LIB_DIR}/libluaosirt.so
}

INSANE_SKIP_${PN} = "dev-so"
FILES_SOLIBSDEV=""
SOLIBS=".so"

FILES_${PN} += " ${LUA_LIB_DIR}/*"
FILES_${PN}-dbg += "${LUA_LIB_DIR}/.debug"
