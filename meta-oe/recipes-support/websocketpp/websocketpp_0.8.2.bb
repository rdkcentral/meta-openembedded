SUMMARY = "C++/Boost Asio based websocket client/server library."
SECTION = "libs/network"
HOMEPAGE = "https://github.com/zaphoyd/websocketpp"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=4d168d763c111f4ffc62249870e4e0ea"

DEPENDS = "openssl boost zlib"

SRC_URI = "git://github.com/zaphoyd/websocketpp.git;protocol=https;branch=master"

# tag 0.8.2
SRCREV= "56123c87598f8b1dd471be83ca841ceae07f95ba"

S = "${WORKDIR}/git"

inherit cmake
