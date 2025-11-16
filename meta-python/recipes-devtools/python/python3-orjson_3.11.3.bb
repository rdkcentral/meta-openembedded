SUMMARY = "orjson is a fast, correct JSON library for Python"
HOMEPAGE = "https://pypi.org/project/orjson/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE-MIT;md5=b377b220f43d747efdec40d69fcaa69d"

SRC_URI[sha256sum] = "1c0603b1d2ffcd43a411d64797a19556ef76958aef1c182f22dc30860152a98a"

require ${BPN}-crates.inc

inherit pypi python_maturin cargo-update-recipe-crates

DEPENDS = "python3-maturin-native"

RDEPENDS:${PN} += "python3-maturin python3-mypy"

do_compile:prepend() {
    sed -i "/panic = \"abort\"/d" ${S}/Cargo.toml
}

BBCLASSEXTEND = "native nativesdk"
