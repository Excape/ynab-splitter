# Env setup

- Create user on mongo:
  ```mongo
  db.createUser({user: "splitter_user", pwd: "12345", roles: [{role: "dbOwner", db: "splitter"}]})
  ```
