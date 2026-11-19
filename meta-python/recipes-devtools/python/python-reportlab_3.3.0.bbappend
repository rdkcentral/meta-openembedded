SRC_URI_remove = "file://reportlab-no-pip.patch"

BBCLASSEXTEND = "native"

# ${PYTHON_PN} gives python2 and not python3
inherit native python3native

DEPENDS_remove = "python-native python-pillow"
DEPENDS += "${PYTHON_PN}-native"

