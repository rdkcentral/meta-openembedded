DESCRIPTION = "A high performance, open source, general-purpose RPC framework. \
Provides gRPC libraries for multiple languages written on top of shared C core library \
(C++, Node.js, Python, Ruby, Objective-C, PHP, C#)"
HOMEPAGE = "https://github.com/grpc/grpc"
SECTION = "libs"
LICENSE = "Apache-2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "gflags c-ares protobuf openssl"

S = "${WORKDIR}/git"
SRCREV = "99fd5c391a435e2677b6caa7fd25089c484a32ab"
BRANCH = "v1.21.x"
SRC_URI = "git://github.com/grpc/grpc.git;protocol=https;branch=${BRANCH} \
           file://0001-CMakeLists.txt-Fix-libraries-installation-for-Linux.patch \
           "
SRC_URI_append_class-target = " file://0001-CMakeLists.txt-Fix-grpc_cpp_plugin-path-during-cross.patch \
                                file://0001-Define-gettid-only-for-glibc-2.30.patch \
                               "

# turn off debug mode
DEBUG_BUILD = "1"

inherit cmake

EXTRA_OECMAKE = " \
    -DgRPC_CARES_PROVIDER=package \
    -DgRPC_ZLIB_PROVIDER=package \
    -DgRPC_SSL_PROVIDER=package \
    -DgRPC_PROTOBUF_PROVIDER=package \
    -DgRPC_GFLAGS_PROVIDER=package \
    -DgRPC_INSTALL=ON \
    -DgRPC_BUILD_CODEGEN=OFF \
    -DgRPC_BUILD_TESTS=OFF \
    -DgRPC_BUILD_CSHARP_EXT=OFF \
    -DCMAKE_CROSSCOMPILING=ON \
    -DBUILD_SHARED_LIBS=ON \
    -DCMAKE_NO_SYSTEM_FROM_IMPORTED=TRUE \
    "

BBCLASSEXTEND = "nativesdk"

SYSROOT_DIRS_BLACKLIST_append_class-target = "${libdir}/cmake/grpc"

FILES_${PN}-dev += "${bindir}\
                    ${libdir}/cmake"
