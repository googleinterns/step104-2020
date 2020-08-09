// Your web app's Firebase configuration
var firebaseConfig = {
  apiKey: "AIzaSyAGk_4TZiVf_TslWON5hNQDYVODuJifP7A",
  authDomain: "com-sdk-dashboard-step-2020.firebaseapp.com",
  databaseURL: "https://com-sdk-dashboard-step-2020.firebaseio.com",
  projectId: "google.com:sdk-dashboard-step-2020",
  messagingSenderId: "412575630040",
  appId: "1:412575630040:web:824b2d31f4e1f06b100dad",
};

// Initialize Firebase
firebase.initializeApp(firebaseConfig);

// Redirect to Google Sign In
async function googleSignIn() {
  firebase.auth().onAuthStateChanged(async function(user) {
    if (user) {
      // User is signed in.
      const userObject = {
        uid: user.uid,
        email: user.email,
        favoriteSDKs: {}
      };
      const response = await fetch('v1/users', {
        method: "POST",
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(userObject)
      });
      console.log(response.json());
    } else {
      // No user is signed in.
      var provider = new firebase.auth.GoogleAuthProvider();
      firebase.auth().signInWithRedirect(provider);
      firebase.auth().getRedirectResult().then(async function(result) {
        if (result.credential) {
          console.info("Successful sign in")
          // The signed-in user info.
          var user = result.user;
          const userObject = {
            uid: user.uid,
            email: user.email,
            favoriteSDKs: {
              "ANDROID": []
            }
          };
          const response = await fetch('v1/users', {
            method: "POST",
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(userObject)
          });
          console.log(response.json());
        }
      }).catch(function(error) {
        console.info("Unsuccessful sign in");
      });
    }
  });
}

// Sign user out of google sign-in
function googleSignOut() {
  firebase.auth().signOut().then(function() {
    console.info("Sign out Successful");
  }).catch(function(error) {
    console.info("Sign out Unsuccessful");
  });
}

function getCurrentUser() {
  return firebase.auth().currentUser;
}

