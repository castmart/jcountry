#!/bin/bash
directory_length=`echo $1 | awk '{print length}'`
echo $directory_length
for filename in $1* ; do
    po_file=${filename:directory_length}
    echo $filename "==>" $po_file "==>" ${po_file%???}
    msgfmt -o $1$2_${po_file%???}.mo $filename
    msgunfmt -p -o $1$2_${po_file%???}.properties $1$2_${po_file%???}.mo
done
