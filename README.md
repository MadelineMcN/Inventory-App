# Requirements

The inventory application allows users to track inventory inside of a grid layout. This inventory management is secured with authentication and allows users to be notified when items are out. 

**Login Screen**
The requirements for the login screen were that users would input an email address and password. Depending on if their login credentials existed in the database, they would either login or register their account. The password needed to be hidden when typed in the edit field. 

**Main Screen**
The inventory application needed to have a grid layout that could be populated with items. The screen needed to have a floating Action button where items could be added. Users needed to have the ability to preform CRUD actions on the items in the grid, including changing the number of items that were in stock.

**SMS Notifications**
The inventory application needed to have a toggle option to turn on notifications that would be sent to the device. When an item was out or low stock, an SMS message would be sent to the user. 


# Design

**Login Screen**
For the login screen I used two edit fields and a button inside of a constraint layout. The constraint layout allowed me to line up my screen elements so everything looked uniform.

**Main Screen**
For the main screen, I used a relative layout that would be populated with card layout elements that would represent the items. From the main screen, the users could see each item, item name, item description, and item amount. If users clicked on the item, they were taken to a screen where details about the item could be edited. If a user swiped left on the item from the main screen, the item would be deleted. 

