SUMMARY = "Utilities to work with patches"
DESCRIPTION = "This package includes the following utilities:\n\
 - combinediff creates a cumulative patch from two incremental patches\n\
 - dehtmldiff extracts a diff from an HTML page\n\
 - filterdiff extracts or excludes diffs from a diff file\n\
 - fixcvsdiff fixes diff files created by CVS that "patch" mis-interprets\n\
 - flipdiff exchanges the order of two patches\n\
 - grepdiff shows which files are modified by a patch matching a regex\n\
 - interdiff shows differences between two unified diff files\n\
 - lsdiff shows which files are modified by a patch\n\
 - recountdiff recomputes counts and offsets in unified context diffs\n\
 - rediff and editdiff fix offsets and counts of a hand-edited diff\n\
 - splitdiff separates out incremental patches\n\
 - unwrapdiff demangles patches that have been word-wrapped"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/twaugh/patchutils"
SRCREV = "3c70aa137ad8c4ea9085a73c0dbc1c6897b6ad45"

S = "${WORKDIR}/git"

inherit autotools
BBCLASSEXTEND = "native"
