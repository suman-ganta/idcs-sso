Sample App to simulate IDCS login sequence.

Endpoints
=========
| Endpoints        | Description          |
| ------------- |:-------------:|
| GET /t/token | This routes to '/t/login' when user is not logged in, returns token when user is logged in.|
| GET /t/login | returns a html with a form that gets submitted by itself|
| POST /t/login| takes form parameters and gets an input param out of it.|
|GET /t/logout| clears logged in state|
|GET /ui/f2.html| get a html that has a script that get the token from the above endpoints.|

f2.html
1. It invokes /t/token
2. If response is of type html, then it parses it and submits the form via javascript, effectively performs login.
3. After login, it retries the token again.
