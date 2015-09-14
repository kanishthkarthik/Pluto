# Pluto

A simple android application that serves as an exmple to display more relevant advertisements to users based on the applications the user has installed on their android device.

Pluto utilizes Quikr Developer API's to query for advertisements from its 'AdsByCategory' API.
The categories Id's are obtained by matching application names with categories provided by Quickr.

Example: App names like 'Runtastic' and 'Google Fit' are related to category '53' (Sports and fitness) of the Quikr 'AdsByCategory' API and advertisements related to Sports and fitness like treadmill's and sports shoes are displayed.

This application requires no special permissions other than INTERNET access. Application details are obtained from the 
'android.content.pm.ApplicationInfo'
and 'android.content.pm.PackageManager' classes.

By implementing this more relevant advertisements based on the user's interest are prioritized in the application without invasion of user privacy even when no search history or other user data is available.
