DESCRIPTION = "Lua is a powerful light-weight programming language designed \
for extending applications."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://doc/readme.html;beginline=318;endline=352;md5=60aa5cfdbd40086501778d9b6ebf29ee"
HOMEPAGE = "http://www.lua.org/"

DEPENDS = "readline"
SRC_URI = "http://www.lua.org/ftp/lua-${PV}.tar.gz;name=tarballsrc \
           file://lua.pc.in \
           "
SRC_URI += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', \
           'http://www.lua.org/tests/lua-${PV}-tests.tar.gz;name=tarballtest \
            file://run-ptest \
           ', '', d)}"


SRC_URI += "file://makefile.patch"

SRC_URI[tarballsrc.md5sum] = "53a9c68bcc0eda58bdc2095ad5cdfc63"
SRC_URI[tarballsrc.sha256sum] = "f681aa518233bc407e23acf0f5887c884f17436f000d453b2491a9f11a52400c"
SRC_URI[tarballtest.md5sum] = "b14fe3748c1cb2d74e3acd1943629ba3"
SRC_URI[tarballtest.sha256sum] = "b80771238271c72565e5a1183292ef31bd7166414cd0d43a8eb79845fa7f599f"

inherit pkgconfig binconfig ptest

TARGET_CC_ARCH += " -fPIC ${LDFLAGS}"
EXTRA_OEMAKE = "'CC=${CC} -fPIC' 'MYCFLAGS=${CFLAGS} -DLUA_USE_LINUX -fPIC' MYLDFLAGS='${LDFLAGS} -Wl,-soname,liblua.so.5' LUA_SO='liblua.so.5.3.4' TO_SOLIB='liblua.so.5.3.4'"

do_configure_prepend() {
    sed -i -e s:/usr/local:${prefix}:g src/luaconf.h
}

do_compile () {
    oe_runmake linux
}

do_install () {
    oe_runmake \
        'INSTALL_TOP=${D}${prefix}' \
        'INSTALL_BIN=${D}${bindir}' \
        'INSTALL_INC=${D}${includedir}/' \
        'INSTALL_MAN=${D}${mandir}/man1' \
        'INSTALL_SHARE=${D}${datadir}/lua' \
        'INSTALL_LIB=${D}${libdir}' \
        'INSTALL_CMOD=${D}${libdir}/lua/5.3' \
        install
    install -d ${D}${libdir}/pkgconfig

    oe_soinstall ${S}/src/liblua.so.5.3.4 ${D}${libdir}
    sed -e s/@VERSION@/${PV}/ ${WORKDIR}/lua.pc.in > ${WORKDIR}/lua.pc
    install -m 0644 ${WORKDIR}/lua.pc ${D}${libdir}/pkgconfig/
    rmdir ${D}${datadir}/lua/5.3
    rmdir ${D}${datadir}/lua
}

do_install_ptest () {
        cp -R --no-dereference --preserve=mode,links -v ${WORKDIR}/lua-${PV}-tests ${D}${PTEST_PATH}/test
}

FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
SOLIBS=".so"

FILES_${PN} += " \
        ${bindir}/l* \
        ${libdir}/*.so* \
        ${libdir}/lua/* \
"

FILES_${PN}-staticdev += " \
        ${libdir}/*.a \
"

FILES_${PN}-dbg += " \
        ${bindir}/.debug \
        ${libdir}/.debug \
"

BBCLASSEXTEND = "native nativesdk"
