# Project-MOBLIMA

## 📁 Folder Structure
```
~
├── doc                   # Documentation (Java doc & UML class diagrams)
├── src                   # Source code
├── .gitignore
└── README.md
```

## ⭐ Getting Started
At the root directory, to compile all ```.java``` files:
```
cd src
javac MovieApp.java -d ../bin
```

At the root directory, to start the application:
```
cd bin
java MovieApp
```

At the root directory, to convert all Javadoc into HTML documentation:
```
cd src
javadoc model -d ../doc/Javadoc
javadoc view -d ../doc/Javadoc
javadoc controller -d ../doc/Javadoc
```


