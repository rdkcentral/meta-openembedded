DESCRIPTION = "\
Netperf is a benchmark that can be used to measure the performance of many \
different types of networking. It provides tests for both unidirectional \
throughput, and end-to-end latency."
HOMEPAGE = "https://hewlettpackard.github.io/netperf/"
SECTION = "console/network"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=e661ab33a2a71ad6652c178dedf8aaa2"

SRC_URI = "git://github.com/HewlettPackard/netperf.git;branch=master"

SRCREV = "3bc455b23f901dae377ca0a558e1e32aa56b31c4"

S = "${WORKDIR}/git"

inherit autotools

do_configure_prepend () {
	#sockaddr sa_len member variable is applicable only for BSD system. Commenting it out.
	echo "Prepend has been called"
	sed -i 's/^\(AC_CHECK_SA_LEN.*\)/#\1/' ${S}/configure.ac
}

do_install () {
        install -d ${D}${bindir}
        install -m 0755 ${B}/src/netperf ${D}${bindir}
}


