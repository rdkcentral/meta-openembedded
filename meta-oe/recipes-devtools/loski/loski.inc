# This file is Confidential Information of CUJO LLC.
# Copyright (c) 2018-2019 CUJO LLC. All rights reserved.

SECTION = "cujo/lib/lua"
SRC_URI = "git://github.com/getCUJO/loski.git;tag=v0.1.0-beta;nobranch=1"
DEPENDS = "lua"
RDEPENDS_${PN} += "lua (>= 5.3)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=05fe8781dcf20c7e79c5da42544bc10f"

LUA_LIB_DIR =  "${libdir}/lua/5.3"
LUA_SHARE_DIR = "${datadir}/lua/5.3"

CFLAGS_prepend = " -std=gnu99 -D_GNU_SOURCE _DLUA_USE_LINUX -DCUJO \
	-I${STAGING_INCDIR} -I${S} "
LDFLAGS_append = " -fPIC -ldl -lrt "

EXTRA_OEMAKE = " \
	'LD=${CC}' \
	'CC=${CC}' \
	INC_PATH=-I${STAGING_INCDIR} \
	LIB_PATH='-L${LUA_LIB_DIR}/socket' \
	LUAPATH=${LUA_SHARE_DIR} \
	LUACPATH=${LUA_LIB_DIR} \
	LUA_INC=${STAGING_INCDIR} \
	LUA_HOME=${STAGING_DIR} \ 
	MYLIBS='-llua' \
"

S = "${WORKDIR}/git"
