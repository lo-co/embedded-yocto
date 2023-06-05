DESCRIPTION = "Recipe to build the 'nano' editor"
#LICENSE="GPLv3"
LICENSE="CLOSED"


PN = "nano"
PV = "7.2"

SITE = "http://www.nano-editor.org/dist"
PV_MAJOR = "${@d.getVar('PV',d,1).split('.')[0]}"
PV_MINOR = "${@d.getVar('PV',d,1).split('.')[1]}"

SRC_URI = "${SITE}/v${PV_MAJOR}/${PN}-${PV}.tar.gz"

# Checksums are verified using linux command line md5sum and shasum for the tar.gz file
#SRC_URI[md5sum] = "2fc38daa9075b7e29f69085c63a5aa8a"
SRC_URI[sha256sum] = "b4edaab0a037b5760484907c674e980053896f33759e43f834a212145fd085f1"
#SRC_URI[sha256sum] = "be68e133b5e81df41873d32c517b3e5950770c00fc5f4dd23810cd635abce67a"

#LIC_FILES_CHKSUM = "${SITE}/v${PV_MAJOR}/COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

python do_fetch() {
   bb.plain("Downloading source tarball from ${SRC_URI} ...")

   src_uri = (d.getVar('SRC_URI', True) or "").split()
   if len(src_uri) == 0:
      bb.fatal("Empty URI")

   try:
      fetcher = bb.fetch2.Fetch(src_uri, d)
      fetcher.download()
   except bb.fetch2.BBFetchException:
      bb.fatal("Could not fetch source tarball.")

   bb.plain("Download successful.")
}

addtask fetch before do_build

python do_unpack() {
   bb.plain("Unpacking source tarball ...")

   os.system("tar x -C ${WORKDIR} -f ${DL_DIR}/${P}.tar.gz")

   bb.plain("Unpacked source tarball.")
}

addtask unpack before do_build after do_fetch


python do_configure() {
   bb.plain("Configuring source package ...")

   os.system("cd ${WORKDIR}/${P} && ./configure")

   bb.plain("Configured source package.")
}

addtask configure before do_build after do_unpack


python do_compile() {
   bb.plain("Compiling package...")

   os.system("cd ${WORKDIR}/${P} && make")

   bb.plain("Compiled package.")
}

addtask compile before do_build after do_configure

#do_clean[nostamp] = "1"
#do_clean() {
#    rm -rf ${WORKDIR/${P}
#    rm -f ${TMPDIR}/stamps/*
#}
# addtask clean

