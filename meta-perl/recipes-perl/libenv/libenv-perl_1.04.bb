SUMMARY = "Perl module that imports environment variables as scalars or arrays"
DESCRIPTION = "Perl maintains environment variables in a special hash named %ENV. \
For when this access method is inconvenient, the Perl module Env allows environment \
variables to be treated as scalar or array variables."

HOMEPAGE = "https://metacpan.org/dist/Env/"
SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0-or-later"

LIC_FILES_CHKSUM = "file://LICENSE;md5=76c1cbf18db56b3340d91cb947943bd3"

SRC_URI = "${CPAN_MIRROR}/authors/id/F/FL/FLORA/Env-${PV}.tar.gz"

SRC_URI[sha256sum] = "d94a3d412df246afdc31a2199cbd8ae915167a3f4684f7b7014ce1200251ebb0"

S = "${UNPACKDIR}/Env-${PV}"

inherit cpan

BBCLASSEXTEND = "native"
