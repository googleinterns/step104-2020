// Sign user out of google sign-in
function googleSignOut() {
    firebase.auth().signOut().then(function() {
        console.info("Sign out Successful");
        }).catch(function(error) {
            console.info("Sign out Unsuccessful");
        });
}
