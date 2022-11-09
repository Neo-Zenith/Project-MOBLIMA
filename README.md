# Project-MOBLIMA
![image](https://user-images.githubusercontent.com/77436548/200524950-eb502aca-088c-4084-bade-7025f98a70b3.png)

## 📁 Folder Structure
```
~
├── .vscode               # Settings for source path
├── bin                   # Executiable files
├── doc                   # Documentation
├── src                   # Source code
|    ├── controller       # Control classes
|    ├── database         # Database 
|    ├── handler          # Helper functions
|    ├── model            # Entity classes
|    ├── view             # Boundary classes
├    └── MovieApp.java    # MOBLIMA App
├── .gitignore
└── README.md
```

## ⭐ Getting Started
At the root directory, to compile all ```.java``` files:
```
javac src/MovieApp.java -d bin
```

At the root directory, to start the application:
```
cd bin
java src.MovieApp
```

At the root directory, to convert all Javadoc into HTML documentation:
```
javadoc src src.controller src.database src.handler src.model src.view -d doc/Javadoc -author -version -noqualifier all
```


