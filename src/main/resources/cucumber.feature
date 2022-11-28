@tag
Feature: Shopping Website
  I want to buy some items from website

  



  Scenario Outline:
  Given go to website <email> <password>
    When add to cart <product>
    And give the adress <comname> <city> <adress> <pin> <num>

    Examples: 
      | email 				               | password | product	    | comname    | city			  | adress             | pin    | num        |
      | ayanghosh13237@gmail.com		 | 222222   | Laptop		  | Deloitte   | Gachibowli	| SaiShabarinath     | 500032 | 9475325911 |
      | ayanghosh084@gmail.com			 | 111111   | Handbag  	  | Accenture	 | Hyderabad	| Balaji             | 712412 | 7479202473 |