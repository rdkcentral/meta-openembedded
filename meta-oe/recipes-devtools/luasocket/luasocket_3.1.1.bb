DESCRIPTION = "LuaSocket is the most comprehensive networking support library for the Lua language."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "lua"
RDEPENDS_${PN} += "lua (>= 5.3)"

PR = "r0"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/getCUJO/luasocket.git"
SRC_URI[md5sum] = "41445b138deb7bcfe97bff957503da8e"
SRC_URI[sha256sum] = "4fd9c775cfd98841299851e29b30176caf289370fea1ff1e00bb67c2d6842ca6"
SRCREV = "f4e9c44b433d05d64ff7f38c16712240a7f22934"
FILESEXTRAPATHS_prepend:= "${THISDIR}/files:"
SRC_URI += "file://makefile.patch"

LUA_LIB_DIR =  "${libdir}/lua/5.3"
LUA_SHARE_DIR = "${datadir}/lua/5.3"

EXTRA_OEMAKE = "\
        CC='${CC}' \
        LD='${CC}' \
	PLAT=linux \
	CDIR='${LUA_LIB_DIR}' \
	LDIR='${LUA_SHARE_DIR}' \
	LUAV=5.3 \
	DESTDIR=${D} \
        LUAINC=-I${STAGING_INCDIR} \
	prefix='' \
        "

do_compile () {
        oe_runmake
}

do_install() {
        oe_runmake install-unix
	rm -fr ${D}${LUA_LIB_DIR}/mime
	rm -fr ${D}${LUA_LIB_DIR}/socket
}

FILES_${PN} = "${LUA_LIB_DIR}/*.so \
               ${LUA_SHARE_DIR}/* \
"

FILES_SOLIBSDEV = ""
