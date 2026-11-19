require php_7.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=fb07bfc51f6d5e0c30b65d9701233b2e"

SRC_URI += "file://change-AC_TRY_RUN-to-AC_TRY_LINK.patch \
            file://0001-acinclude.m4-skip-binconfig-check-for-libxml.patch \
            file://0001-fix-error-caused-by-a-new-variable-is-declared-after.patch \
           "
SRC_URI_append_class-target = " \
                                file://pear-makefile.patch \
                                file://phar-makefile.patch \
                                file://0001-opcache-config.m4-enable-opcache.patch \
                                "

SRC_URI[md5sum] = "657fc8315b946c91b3ba2de25b60eea5"
SRC_URI[sha256sum] = "f56132d248c7bf1e0efc8a680a4b598d6ff73fc6b9c84b5d7b539ad8db7a6597"

PACKAGECONFIG[mysql] = "--with-mysqli=${STAGING_BINDIR_CROSS}/mysql_config \
                        --with-pdo-mysql=${STAGING_BINDIR_CROSS}/mysql_config \
                        ,--without-mysqli --without-pdo-mysql \
                        ,mysql5"

FILES_${PN}-fpm += "${sysconfdir}/php-fpm.d/www.conf.default"
