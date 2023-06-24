#!/bin/bash
directory_length=`echo $1 | awk '{print length}'`
echo $directory_length
for filename in $1* ; do
    po_file=${filename:directory_length}
    echo $filename "==>" $po_file "==>" ${po_file%???}
    msgfmt -o $1iso_3166-1_${po_file%???}.mo $filename
    msgunfmt -p -o $1iso_3166-1_${po_file%???}.properties $1iso_3166-1_${po_file%???}.mo
done
