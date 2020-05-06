Reverse Transpose Source Data
=============================

id|country|product_line_item|Product_wing|Division|region|territory|item_id|unique_id|Store_name|Item1|Item2|Item3|Item4|Item5|Item6|Item7|Item8|Item9|Item10
1|India|Product-C|(Product-C)India_West|India_North[DIV](C)|Uttarakhand[Reg](C)|UT[Terr](C)|9|1000655|Queen of Himalaya|750|850|1500|150|1000||2900|2500|1200|5050
1|India|Product-A|(Product-A)India_West|India_North[DIV](A)|Himachal[Reg](A)|HM[Terr](A)|12|1000416|Snow Peaks|765|90

Reverse Transpose Output Data
=============================

+---+-------+-----------------+---------------------+-------------------+-------------------+-----------+-------+---------+-----------------+------+-----+
|id |country|product_line_item|Product_wing |Division |region |territory |item_id|unique_id|Store_name |key |value|
+---+-------+-----------------+---------------------+-------------------+-------------------+-----------+-------+---------+-----------------+------+-----+
|1 |India |Product-C |(Product-C)India_West|India_North[DIV](C)|Uttarakhand[Reg](C)|UT[Terr](C)|9 |1000655 |Queen of Himalaya|Item1 |750 |
|1 |India |Product-C |(Product-C)India_West|India_North[DIV](C)|Uttarakhand[Reg](C)|UT[Terr](C)|9 |1000655 |Queen of Himalaya|Item2 |850 |
|1 |India |Product-C |(Product-C)India_West|India_North[DIV](C)|Uttarakhand[Reg](C)|UT[Terr](C)|9 |1000655 |Queen of Himalaya|Item3 |1500 |
|1 |India |Product-C |(Product-C)India_West|India_North[DIV](C)|Uttarakhand[Reg](C)|UT[Terr](C)|9 |1000655 |Queen of Himalaya|Item4 |150 |
|1 |India |Product-C |(Product-C)India_West|India_North[DIV](C)|Uttarakhand[Reg](C)|UT[Terr](C)|9 |1000655 |Queen of Himalaya|Item5 |1000 |
|1 |India |Product-C |(Product-C)India_West|India_North[DIV](C)|Uttarakhand[Reg](C)|UT[Terr](C)|9 |1000655 |Queen of Himalaya|Item6 |null |
|1 |India |Product-C |(Product-C)India_West|India_North[DIV](C)|Uttarakhand[Reg](C)|UT[Terr](C)|9 |1000655 |Queen of Himalaya|Item7 |2900 |
|1 |India |Product-C |(Product-C)India_West|India_North[DIV](C)|Uttarakhand[Reg](C)|UT[Terr](C)|9 |1000655 |Queen of Himalaya|Item8 |2500 |
|1 |India |Product-C |(Product-C)India_West|India_North[DIV](C)|Uttarakhand[Reg](C)|UT[Terr](C)|9 |1000655 |Queen of Himalaya|Item9 |1200 |
|1 |India |Product-C |(Product-C)India_West|India_North[DIV](C)|Uttarakhand[Reg](C)|UT[Terr](C)|9 |1000655 |Queen of Himalaya|Item10|5050 |
|1 |India |Product-A |(Product-A)India_West|India_North[DIV](A)|Himachal[Reg](A) |HM[Terr](A)|12 |1000416 |Snow Peaks |Item1 |765 |
|1 |India |Product-A |(Product-A)India_West|India_North[DIV](A)|Himachal[Reg](A) |HM[Terr](A)|12 |1000416 |Snow Peaks |Item2 |900 |
|1 |India |Product-A |(Product-A)India_West|India_North[DIV](A)|Himachal[Reg](A) |HM[Terr](A)|12 |1000416 |Snow Peaks |Item3 |null |
|1 |India |Product-A |(Product-A)India_West|India_North[DIV](A)|Himachal[Reg](A) |HM[Terr](A)|12 |1000416 |Snow Peaks |Item4 |null |
|1 |India |Product-A |(Product-A)India_West|India_North[DIV](A)|Himachal[Reg](A) |HM[Terr](A)|12 |1000416 |Snow Peaks |Item5 |1200 |
|1 |India |Product-A |(Product-A)India_West|India_North[DIV](A)|Himachal[Reg](A) |HM[Terr](A)|12 |1000416 |Snow Peaks |Item6 |1440 |
|1 |India |Product-A |(Product-A)India_West|India_North[DIV](A)|Himachal[Reg](A) |HM[Terr](A)|12 |1000416 |Snow Peaks |Item7 |3500 |
|1 |India |Product-A |(Product-A)India_West|India_North[DIV](A)|Himachal[Reg](A) |HM[Terr](A)|12 |1000416 |Snow Peaks |Item8 |2600 |
|1 |India |Product-A |(Product-A)India_West|India_North[DIV](A)|Himachal[Reg](A) |HM[Terr](A)|12 |1000416 |Snow Peaks |Item9 |null |
|1 |India |Product-A |(Product-A)India_West|India_North[DIV](A)|Himachal[Reg](A) |HM[Terr](A)|12 |1000416 |Snow Peaks |Item10|6000 |
+---+-------+-----------------+---------------------+-------------------+-------------------+-----------+-------+---------+

------------------------------------------------------------------------------------------------------------------------------------------

Dynamic Transpose Source File
==============================

idoc_number,orderid,idoc_qualifier_org,idoc_org
7738,2364,6,0
7738,2364,7,0
7738,2364,8,mystr1
7738,2364,12,mystr2
7739,2365,12,mystr3
7739,2365,7,mystr4

Lookup Table 
============

qualifier,desc
6,Division
7,Distribution Channel
8,Sales Org
12,Order type

Dynamic Transpose Output File
==============================
idoc_number,order_id,Division,Distribution Channel,Sales org,Order Type
7738,2364,0,0,mystr1,mystr2
7739,2365,null,mystr3,null,mystr4


-------------------------------------------------------------------------------------------------------------------------------------------

Fixed Width File Source Data
=============================
 10597    Subhasish Guha                                    subhasish.iot@gmail.com                 27           TXNPUES                    Yes          5007.10
191675    Ritam Mukherjee                                   ritam@gmail.com                         29 SINGLE    OUNHQEX XUFQONY            No 01/14/20133172.53   43537.00  
          Saurav Agarwal                                    agarwalsaurav@gmail.com                 30 MARRIED                                           100000.00 7000.00
 80495    Priyanshu Kumar                                   kumarpriyanshu@gmail.com                45 MARRIED   XDZANTV    
 
 
Fixed Width Metadata File 
==========================

col_name,size
account_id,10
name,50
mail,40
age,3
status,10
desc,27
cflow,3
process_date,10
debit_amt,10
credit_amt,10

Fixed Width File Output Data
=============================
+----------+---------------+------------------------+---+-------+---------------+-----+------------+---------+----------+
|account_id|name           |mail                    |age|status |desc           |cflow|process_date|debit_amt|credit_amt|
+----------+---------------+------------------------+---+-------+---------------+-----+------------+---------+----------+
|10597     |Subhasish Guha |subhasish.iot@gmail.com |27 |       |TXNPUES        |Yes  |            |5007.10  |          |
|191675    |Ritam Mukherjee|ritam@gmail.com         |29 |SINGLE |OUNHQEX XUFQONY|No   |01/14/2013  |3172.53  |43537.00  |
|          |Saurav Agarwal |agarwalsaurav@gmail.com |30 |MARRIED|               |     |            |100000.00|7000.00   |
|80495     |Priyanshu Kumar|kumarpriyanshu@gmail.com|45 |MARRIED|XDZANTV        |No   |11/21/2012  |         |          |
+----------+---------------+------------------------+---+-------+---------------+-----+------------+---------+----------+

