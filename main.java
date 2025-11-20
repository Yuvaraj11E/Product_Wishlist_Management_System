import java.sql.SQLException;
import java.util.Scanner;

public class main {

    private static final String ap = "123";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        user userDAO = new user();
        product productDAO = new product();
        wishlist wishlistDAO = new wishlist();

        boolean running = true;

        while (running) {
            System.out.println("\n--- Product Wishlist Management System ---");
            System.out.println("\n ~~~ Main Menu ~~~ ");
            System.out.println("\n==================================");
            System.out.println("1. Create User");
            System.out.println("2. Login as User");
            System.out.println("3. Delete User");
            System.out.println("4. Login as Admin");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1: 
                    System.out.print("Enter Username: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    if (age <= 0 || age > 120) {
                        System.out.println("Enter a valid age.");
                        break;
                    }

                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    int userId = userDAO.createUser(name, age, address, password);

                    if (userId != -1) {
                        System.out.println("User created successfully. Your User ID is: " + userId);
                    } else {
                        System.out.println("User creation failed.");
                    }

                    break;

                case 2: 
                    System.out.print("Enter Username: ");
                    String uname = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String pass = sc.nextLine();

                    int userId1 = userDAO.login(uname, pass);
                    if (userId1 != -1) {
                        System.out.println("Login successful.");
                        boolean loggedIn = true;

                        while (loggedIn) {
                            System.out.println("\n--- Welcome to our online shopping portal, " + uname + " ---");
                            System.out.println("1. View Products");
                            System.out.println("2. Add to Wishlist");
                            System.out.println("3. Delete from Wishlist");
                            System.out.println("4. View Wishlist");
                            System.out.println("5. Logout");
                            System.out.print("Choose an option: ");
                            int loginChoice = sc.nextInt();

                            switch (loginChoice) {
                                case 1:
                                    productDAO.viewProducts();
                                    break;
                                case 2:
                                    if (wishlistDAO.getWishlistCount(userId1) >= 10) {
                                        System.out.println("You can only have up to 10 items in your wishlist.");
                                    } else {
                                        System.out.print("Enter Product ID to add: ");
                                        int pidAdd = sc.nextInt();
                                        if (!productDAO.checkProductExists(pidAdd)) {
                                            System.out.println("Product ID does not exist.");
                                            break;
                                        }

                                        System.out.print("Enter quantity (1-10): ");
                                        int qty = sc.nextInt();
                                        if (qty < 1 || qty > 10) {
                                            System.out.println("Invalid quantity.");
                                            break;
                                        }

                                        if (wishlistDAO.addToWishlist(userId1, pidAdd, qty)) {
                                            System.out.println("Product added to wishlist.");
                                        }
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter Product ID to remove: ");
                                    int pidDel = sc.nextInt();
                                    if (wishlistDAO.deleteFromWishlist(userId1, pidDel)) {
                                        System.out.println("Product removed.");
                                    } else {
                                        System.out.println("Product not found in wishlist.");
                                    }
                                    break;
                                case 4:
                                    if (!wishlistDAO.viewWishlist(userId1)) {
                                        System.out.println("No items to display!");
                                    }
                                    break;
                                case 5:
                                    System.out.println("Logged out successfully.");
                                    loggedIn = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice!");
                            }
                        }
                    } else {
                        System.out.println("Login failed. Invalid credentials.");
                    }
                    break;

                case 3: 
                    System.out.print("Enter Username: ");
                    String delUser = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String delPass = sc.nextLine();
                    if (userDAO.deleteUser(delUser, delPass)) {
                        System.out.println("User deleted.");
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;

                case 4: 
                    System.out.print("Enter Admin Password: ");
                    String adminPass = sc.nextLine();

                    if (adminPass.equals(ap)) {
                        System.out.println("Admin login successful.");
                        boolean adminLoggedIn = true;

                        while (adminLoggedIn) {
                            System.out.println("\n--- Admin Panel ---");
                            System.out.println("1. View All Products");
                            System.out.println("2. Add Product");
                            System.out.println("3. Logout");
                            System.out.print("Choose an option: ");
                            int adminChoice = sc.nextInt();
                            sc.nextLine();

                            switch (adminChoice) {
                                case 1:
                                    productDAO.viewProducts();
                                    break;
                                case 2:
                                    System.out.print("Enter Product ID: ");
                                    int pid = sc.nextInt();
                                    sc.nextLine();

                                    System.out.print("Enter Product Name: ");
                                    String pname = sc.nextLine();

                                    System.out.print("Enter Price: ");
                                    double price = sc.nextDouble();
                                    sc.nextLine();

                                    System.out.print("Enter Rating (1 to 5): ");
                                    double rating = sc.nextDouble();
                                    sc.nextLine();

                                  

                                    if (productDAO.addProduct(pid, pname, price, rating))
                                    {
                                        System.out.println("Product added successfully.");
                                    } 
                                    break;
                                case 3:
                                    adminLoggedIn = false;
                                    System.out.println("Admin logged out.");
                                    break;
                                default:
                                    System.out.println("Invalid admin choice.");
                            }
                        }
                    } else {
                        System.out.println("Incorrect admin password.");
                    }
                    break;

                case 5:
                    running = false;
                    DBconnection.closeConnection();
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}




