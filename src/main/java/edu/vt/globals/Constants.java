/*
 * Created by Osman Balci on 2022.10.17
 * Copyright © 2021 Osman Balci. All rights reserved.
 */
package edu.vt.globals;

public final class Constants {

    //---------------
    // To run locally
    //---------------

    // Windows
    public static final String PHOTOS_ABSOLUTE_PATH = "/Users/Shren/Docroot/BTStorage/PhotoStorage";

    // Unix (macOS) or Linux
//    public static final String PHOTOS_ABSOLUTE_PATH = "/Users/Balci/DocRoot/BTStorage/";

    //-------------------------------------------------------------------
    // To run on your AWS EC2 instance, on VENUS or JUPITER course server
    //-------------------------------------------------------------------
    //public static final String PHOTOS_ABSOLUTE_PATH = "/opt/wildfly/DocRoot/BTStorage/";

    /*
     ---------------------------------
     To Deploy to Your AWS EC2 server:
     ---------------------------------
     STEP 1: Comment out the two constants under "To run locally" above.
     STEP 2: Uncomment the two constants under "To run on your AWS EC2 instance" above.
     STEP 3: Comment out the two constants under "To run locally" below.
     STEP 4: Uncomment the two constants under "To run on your AWS EC2 instance with your IP address" below.
     STEP 5: Replace 54.92.194.218 with the public IP address of your AWS EC2 instance.
     STEP 6: Select Build --> Rebuild Project.
     STEP 7: Run your app to generate the WAR file. Do not use the app locally!
     STEP 8: Use the generated WAR file to deploy your app to your AWS EC2 server.
     STEP 9: Undo the above changes to run the app locally.
     */

    /*
    =================================================================================================
    |   For displaying external files to the user in an XHTML page, we use the Undertow subsystem.  |
    =================================================================================================
     We configured WildFly Undertow subsystem so that
     http://localhost:8080/btuserphotos/p displays file p from /Users/cdost/DocRoot/BTStorage/
     */

    //---------------
    // To run locally
    //---------------
    public static final String PHOTOS_URI = "http://localhost:8080/btuserphotos/";

    //-----------------------------------------------------
    // To run on your AWS EC2 instance with your IP address
    //-----------------------------------------------------
    //public static final String PHOTOS_URI = "http://100.25.47.142:8080/btuserphotos/";

    //-----------------------------------------
    // To run on VENUS or JUPITER course server
    //-----------------------------------------
//    public static final String PHOTOS_URI = "https://venus.cs.vt.edu/btuserphotos/";

    /*
    =============================================
    |   Our Design Decision for Profile Photo   |
    =============================================
    We do not want to use the uploaded user profile photo as is, which may be very large
    degrading performance. We scale it down to size 200x200 called the Thumbnail photo size.
     */
    public static final Integer THUMBNAIL_SIZE = 200;

    /*
     United States postal state abbreviations (codes)
     */
    public static final String[] STATES = {"AK", "AL", "AR", "AZ", "CA", "CO", "CT",
            "DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA",
            "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM",
            "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC", "SD", "TN", "TX", "UT",
            "VA", "VI", "VT", "WA", "WI", "WV", "WY"};

    /*
     A security question is selected and answered by the user at the time of account creation.
     The selected question/answer is used as a second level of authentication for
     (a) resetting user's password, and (b) deleting user's account.
     */
    public static final String[] SECURITY_QUESTIONS = {
            "In what city or town did your mother and father meet?",
            "In what city or town were you born?",
            "What did you want to be when you grew up?",
            "What do you remember most from your childhood?",
            "What is the name of the boy or girl that you first kissed?",
            "What is the name of the first school you attended?",
            "What is the name of your favorite childhood friend?",
            "What is the name of your first pet?",
            "What is your mother's maiden name?",
            "What was your favorite place to visit as a child?"
    };

    public static final String[] ROUTES =
            {
                    "CRB - Carpenter Blvd",
                    "CRC - Corporate Research Center",
                    "BLU - Explorer Blue",
                    "GLD - Explorer Gold",
                    "HDG - Harding Ave",
                    "HWD - Hethwood",
                    "HWA - Hethwood A",
                    "HWB - Hethwood B",
                    "HXP - Hokie Express",
                    "MSN - Main Street North",
                    "MSS - Main Street South",
                    "MSG - North Main Givens",
                    "PHB - Patrick Henry B",
                    "PHD - Patrick Henry Dr",
                    "PRO - Progress Street",
                    "MSA - South Main Airport Rd",
                    "TOM - Toms Creek",
                    "TTT - Two Town Trolley",
                    "UCB - University City Blvd"
            };
    
    public static final String[] BUS_STOPS =
            {
                    "Newman Library",
                    "Burruss Hall",
                    "Davidson Hall",
                    "West Campus/Perry Nbnd",
                    "Old Security Bldg",
                    "Stanger/Old Turner Wbnd",
                    "Hutcheson Hall",
                    "War Memorial Hall",
                    "Squires Ebnd",
                    "Alumni Mall Ebnd",
                    "Alumni Mall Wbnd",
                    "Squires Wbnd",
                    "Torgersen Hall",
                    "Wright House",
                    "Litton Reaves Hall",
                    "Overflow Lot Wbnd",
                    "Oak Lane North",
                    "Oak Lane South",
                    "Overflow Lot Ebnd",
                    "I Lot/Cage Sbnd",
                    "Food Science Bldg",
                    "McComas Hall",
                    "Cassell Coliseum",
                    "Tennis Courts",
                    "Harper Hall",
                    "Lane Stadium S Endzone Sbnd",
                    "Lane Stadium S Endzone Nbnd",
                    "Coliseum Parking Lot Sbnd",
                    "Oak Lane Nbnd",
                    "Bioinfomatics Bldg",
                    "Life Sciences 1 Bldg",
                    "Smithfield Lot",
                    "Smithfield/Duck Pond Sbnd",
                    "Cage/Smithfield Ebnd",
                    "Cage/Duck Pond Ebnd",
                    "Cage/Duck Pond Wbnd",
                    "Cage/Smithfield Wbnd",
                    "Prices Fork/Old Glade Wbnd",
                    "Vet School Wbnd",
                    "Prices Fork/Plantation Wbnd",
                    "Prices Fork/Huntington Wbnd",
                    "Hethwood Square on Hethwood",
                    "Tall Oaks/Hethwood Ebnd",
                    "Tall Oaks/Foxhunt Ebnd",
                    "Tall Oaks/Heather Ebnd",
                    "Tall Oaks/Foxtrail Sbnd",
                    "Stroubles Cr",
                    "Tall Oaks/Copper Croft Nbnd",
                    "Heather/Tall Oaks Nbnd",
                    "Heather/Plymouth Nbnd",
                    "Heather/Huntington Nbnd",
                    "Prices Fork/Huntington Ebnd",
                    "Prices Fork/Plantation Ebnd",
                    "Prices Fork/Old Glade Ebnd",
                    "Heather/Plymouth Sbnd",
                    "Carpenter Blvd/Redd Circle",
                    "Tall Oaks/Colonial Sbnd",
                    "Toms Creek/Winston Nbnd",
                    "Gilbert Linkous Nbnd",
                    "Toms Creek/Watson Nbnd",
                    "Toms Creek/McBryde Nbnd",
                    "Broce/Toms Creek Ebnd",
                    "Progress/Broce Nbnd",
                    "Progress/University Terr Nbnd",
                    "Progress/Patrick Henry Nbnd",
                    "The Village on Patrick Henry Wbnd",
                    "Patrick Henry/Toms Creek Wbnd",
                    "University City/Toms Creek Wbnd",
                    "Shawnee on University City Wbnd",
                    "Shawnee on University City Sbnd",
                    "University City/Broce Sbnd",
                    "University City/Glade Sbnd",
                    "University Mall Sbnd",
                    "University Mall Nbnd",
                    "University City/Broce Nbnd",
                    "Shawnee on University City Nbnd",
                    "Shawnee on University City Ebnd",
                    "University City/Toms Creek Ebnd",
                    "Patrick Henry/Toms Creek Ebnd",
                    "The Village on Patrick Henry Ebnd",
                    "Patrick Henry/Progress Ebnd",
                    "Progress/Hunt Club Sbnd",
                    "Progress/University Terr Sbnd",
                    "Progress/Broce Sbnd",
                    "Toms Creek/McBryde Sbnd",
                    "Toms Creek/Watson Sbnd",
                    "Gilbert Linkous Sbnd",
                    "Toms Creek/Winston Sbnd",
                    "Prices Fork/Turner Ebnd",
                    "Toms Creek/Hunt Club Nbnd",
                    "Toms Creek/Hunt Club Sbnd",
                    "Progress/Watson Nbnd",
                    "Progress/Watson Sbnd",
                    "Main/Montgomery Nbnd",
                    "Main/Lucas Nbnd S",
                    "Lucas/Main Ebnd",
                    "Lucas/Giles Ebnd",
                    "Giles/Northview Nbnd",
                    "Giles/Heights Nbnd",
                    "Giles/Patrick Henry Nbnd",
                    "Patrick Henry/Giles Wbnd",
                    "Patrick Henry/Main Wbnd",
                    "1500 North Main Nbnd",
                    "Main/Giles Nbnd",
                    "Main/Red Maple Nbnd",
                    "Givens/Main Wbnd",
                    "Whipple/Givens Sbnd",
                    "Pheasant Run",
                    "Seneca/Patrick Henry Sbnd",
                    "Main/Patrick Henry Sbnd",
                    "1200 North Main Sbnd",
                    "Main/Northview Sbnd",
                    "Main/Lucas Sbnd",
                    "Main/Montgomery Sbnd",
                    "Main/Kabrich Sbnd",
                    "Main/Collegiate Ct Sbnd",
                    "Main/Turner Sbnd",
                    "Main/Lucas Nbnd N",
                    "Main/Northview Nbnd",
                    "1200 North Main Nbnd",
                    "Main/Patrick Henry Nbnd",
                    "Whipple/Courtney Sbnd",
                    "1500 North Main Sbnd",
                    "Main/Turner Nbnd",
                    "Patrick Henry/Mary Jane Ebnd",
                    "Patrick Henry/Seneca Ebnd",
                    "Whipple/Main Sbnd",
                    "Givens/Whipple Ebnd",
                    "Givens/Yale Ebnd",
                    "Patrick Henry/Mary Jane Wbnd",
                    "Roanoke/Church Ebnd",
                    "Roanoke/Wharton Ebnd",
                    "Roanoke/Rutledge Ebnd",
                    "Roanoke/Woolwine Ebnd",
                    "Harding/Silverleaf Ebnd",
                    "Harding/Patrick Henry Ebnd",
                    "Harding/Apperson Ebnd",
                    "Harding/Vista Ebnd",
                    "Harding/Roanoke Ebnd",
                    "Ascot/Harding Ebnd",
                    "Ascot/Hampton",
                    "Ascot/Harding Wbnd",
                    "Harding/Rucker Wbnd",
                    "Harding/Sutton Wbnd",
                    "Harding/Apperson Wbnd",
                    "Harding/Patrick Henry Wbnd",
                    "Harding/Silverleaf Wbnd",
                    "Roanoke/Woolwine Wbnd",
                    "Roanoke/Rutledge Wbnd",
                    "Roanoke/Wharton Wbnd",
                    "Roanoke/Penn Wbnd",
                    "Main/Roanoke Sbnd",
                    "Blacksburg Municipal Building",
                    "Main/Eheart Sbnd",
                    "Main/Eakin Sbnd",
                    "Main/Airport Sbnd",
                    "Main/Faystone Sbnd",
                    "Gables Shopping Center",
                    "Main/Landsdowne Sbnd",
                    "Fairfax/Ellett Ebnd",
                    "Fairfax/New Kent Ebnd",
                    "New Kent/Loudon Ebnd",
                    "New Kent/Sussex Ebnd",
                    "Grissom/Nellies Cave Nbnd",
                    "Marlington/Grissom Wbnd",
                    "Marlington/Emerald Wbnd",
                    "Marlington/Grayland Wbnd",
                    "Main/Landsdowne Nbnd",
                    "Main/Ardmore Nbnd",
                    "Blacksburg Square",
                    "Main/Cohee Nbnd",
                    "Main/Sunset Nbnd",
                    "Main/Graves Nbnd",
                    "Main/Hemlock Nbnd",
                    "Main/Eakin Nbnd",
                    "Main/Clay Nbnd",
                    "Main/Lee Nbnd",
                    "Main St Post Office",
                    "Main/Hemlock Sbnd",
                    "Industrial Park/Transportation Res Ebnd",
                    "Prosperity/Industrial Park Nbnd",
                    "Professional Park Nbnd",
                    "Main/Cedar Pointe Nbnd",
                    "Main/S Hill Nbnd",
                    "Fairfax Circle",
                    "Main/S Hill Sbnd",
                    "Main/Cedar Pointe Sbnd",
                    "Main/South Park Sbnd",
                    "Industrial Park/Transportation Res Wbnd",
                    "Main/King Sbnd",
                    "Blacksburg Transit",
                    "Commerce/Industrial Park Sbnd",
                    "Industrial Park/Prosperity Wbnd",
                    "Country Club/Main Wbnd",
                    "Country Club/Draper Wbnd",
                    "Country Club/Airport Wbnd",
                    "Airport/Fairview Sbnd",
                    "Hubbard/Kennedy Ebnd",
                    "Pratt Dr/Garvin Bldg Sbnd",
                    "Pratt/Kraft Ebnd",
                    "Airport",
                    "Pratt Dr/Andrews Bldg Sbnd",
                    "Kraft/Tech Center Dr Sbnd",
                    "Pratt/Kraft Wbnd",
                    "Pratt Dr/Garvin Bldg Nbnd",
                    "Pratt Dr/Andrews Bldg Nbnd",
                    "Kraft/Research Ctr Ebnd",
                    "Research Ctr/Rimrock Sbnd",
                    "Research Ctr/Rimrock Nbnd",
                    "Research Ctr/N Knollwood Nbnd",
                    "Kraft/Knowledgeworks 1 Bldg Sbnd",
                    "Kraft/Moss Bldg Sbnd",
                    "Research Ctr/S Knollwood Nbnd",
                    "Beamer/Southgate Nbd",
                    "Research Ctr/Innovation Sbnd",
                    "Research Ctr/Innovation Nbnd",
                    "Innovation/Smoot Wbnd",
                    "Chicken Hill/Southgate Sbnd",
                    "Uptown Mall",
                    "NRV Theatre",
                    "Walmart",
                    "LewisGale Hospital Montgomery",
                    "Montgomery County Govt Center",
                    "Republic/Salem Nbnd",
                    "Depot/Cambria Wbnd",
                    "Aquatic Center",
                    "Depot/New Wbnd",
                    "W Main/Dunkley Ebnd",
                    "Montgomery County Courthouse",
                    "Roanoke/Evans Ebnd",
                    "Recreation Center",
                    "Simmons/Hammes Nbnd",
                    "Exit 118 Park and Ride",
                    "Roanoke/Hungate Wbnd",
                    "Roanoke/East Wbnd",
                    "Roanoke/Craig Wbnd",
                    "Roanoke/Evans Wbnd",
                    "Christiansburg Town Hall",
                    "Roanoke/Roberts Ebnd",
                    "Radford/College Ebnd",
                    "Electric Way/Fisher St Wbnd",
                    "Simpson Rd/Village Ln Wbnd",
                    "Electric Way/Fisher St Ebnd",
                    "North Franklin/Church Nbnd",
                    "Church/Brown Ebnd",
                    "Montgomery/Cambria Ebnd",
                    "Cambria/Church Nbnd",
                    "Cambria/Progress Nbnd",
                    "Cambria/Tyler Nbnd",
                    "Central/Cambria Wbnd",
                    " stop_name",
                    "Central/North Franklin Wbnd",
                    "North Franklin/Church Sbnd",
                    "Roanoke/First Ebnd",
                    "Nugget Ridge Circle",
                    "Nugget Ridge/Radford Nbnd",
                    "Radford/Quesenberry Ebnd",
                    "Radford/Depot Ebnd",
                    "Roanoke/Hungate Ebnd",
                    "Roanoke/Falling Branch Ebnd",
                    "Roanoke/Falling Branch Wbnd",
                    "Main/Hickock Wbnd",
                    "Radford/College Wbnd",
                    "Radford/Bower Wbnd",
                    "Depot/Sheltman Ebnd",
                    "Conston/Spradlin Farm Wbnd",
                    "Farmview/Ridinger Ebnd",
                    "Laurel/Sycamore Ebnd",
                    "DMV on Arbor",
                    "Post Office on Arbor",
                    "Arbor/Market",
                    "Market/Shoppers Way Sbnd",
                    "Shoppers Way Wbnd",
                    "Shoppers Way Ebnd",
                    "North Franklin/Wades Nbnd",
                    "North Franklin/Wades Sbnd",
                    "North Franklin/Cambria Nbnd",
                    "N Franklin/Graham Sbnd"
            };
}
