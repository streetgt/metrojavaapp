#!/usr/bin/env python
# -*- coding: utf-8 -*-

import json, sys
from pprint import pprint

reload(sys)
sys.setdefaultencoding('utf-8')

file = sys.argv[1]
file_info = file.split('.')

with open(file) as data_file:
	data = json.load(data_file)
	
save_file = open(file_info[0]+".txt","w")

for data_ex in data:
	#str = ("ID: %d - Name: %s - Lat: %f - Long %f\n" % ( data_ex['id'],data_ex['stop_name'],data_ex['point']['coordinates'][1],data_ex['point']['coordinates'][0] ))	
	str = ("%d;%s;%f;%f\n" % ( data_ex['id'],data_ex['stop_name'],data_ex['point']['coordinates'][1],data_ex['point']['coordinates'][0] ))	
	save_file.write(str)

save_file.close()
 
