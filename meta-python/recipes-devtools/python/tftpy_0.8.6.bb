SUMMARY = "Tftpy is a TFTP library for the Python programming language. It includes client and server classes, with sample implementations."
DESCRIPTION = "Tftpy is a TFTP library for the Python programming language. It \
includes client and server classes, with sample implementations. Hooks are \
included for easy inclusion in a UI for populating progress indicators. It \
supports RFCs 1350, 2347, 2348 and the tsize option from RFC 2349."

HOMEPAGE = "https://github.com/msoulier/tftpy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=22770e72ae03c61f5bcc4e333b61368d"

SRC_URI[sha256sum] = "f616f6a43a36d481c266573608597b9dd3c7c63818415d72aa04f1d1795480ea"

inherit pypi python_setuptools_build_meta
