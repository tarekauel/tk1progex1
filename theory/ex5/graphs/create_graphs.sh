#!/usr/bin/env bash
pdflatex -shell-escape graphs.tex && for i in $( find . | grep out-graphs | grep pdf ); do sips -s format png $i --out $i.png ; done && for i in {0..8}; do mv out-graphs-figure$i.pdf.png graph$i.png; done && rm out-* && rm graphs.pdf && rm graphs.aux && rm graphs.auxlock && rm graphs.log
