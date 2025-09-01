# delivery-app
Simple delivery app for the Laba-Solvd's automation course

Code made by Julian Steven Lozano Penagos for the Java test automation course for Lava.Solvd

External references used: 
 - https://www.geeksforgeeks.org/java/enum-in-java/

Project structure:
- enums: constants used to set up principal model attributes
    * DeliveryStatus: PREPARING, ON_THE_WAY, NOT_ACCEPTED, DELIVERED
    * OrderStatus: PREPARING, CANCELLED, ACCEPTED, PENDING, REFUSED
    * PaymentMethod: CASH, CREDIT_CARD, DEBIT_CARD, DIGITAL_WALLET, PAYPAL
    * UserRole: COSTUMER, COURIER, RESTAURANT_MANAGER
    * VehicleType: WALKING, BICYCLE, CAR, SCOOTER, STEP_VAN
- Interfaces: 
    * MenuManageable
    * Orderable
    * Payable
    * Rateable
    * Trackable
- model: principal classes of the application:
    * User (abstract class)
    * Costumer (subclass of User)
    * Courier (subclass of User)
    * Manager (subclass of User)
    * FoodEstablishment (abstract class)
    * Restaurant (subclass of FoodEstablishment)
    * Supermarket (subclass of FoodEstablishment)
    * MenuItem (abstract class)
    * RestaurantMenuItem (subclass of MenuItem)
    * SupermarketItem (subclass of FoodEstablishment)
    * Order
    * OrderItem
    * Payment (abstract class)
    * CashPayment (subclass of Payment)
    * DigitalPayment (subclass of Payment)
    * Location (abstract class)
    * CustomerLocation (subclass of Location)
    * CourierLocation (subclass of Location)
    * EstablishmentLocation (subclass of Location)

- Controller
    * UserController: abstract class for the creation of users and simple logging
      * CourierController
      * CustomerController
      * ManagerController
    * FoodEstablishmentController: common methods shared by FoodEstablishmentService & RestaurantService 
      * ResturantController: creation of a restaurant associate to a manager and creation of menuItems
      * SupermarketController: creation of a supermarket associate to a manager and creation of menuItems
    * OrderController: Display of available restaurants and theirs menu items
    * PaymentController: simple logic to process payments (under construction)
    * SessionController: manage the menus that are display in terminal
    * DashboardLoaderController
    * GenericUserController

Steps to use the app so far:
1. Create users ( at least a costumer and a restaurant manager)
2. log into and account, menu options are different for each role
   - Restaurant manager:
     * creation of a restaurant
     * creation of a supermarket
     * Manage food establishments
     * creation of a menu item
   - Costumer:
     * Display of the available restaurants
     * Display orders
     * Update profile
   - Courier:
     * No features added