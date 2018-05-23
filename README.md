# Documentation

## Installation
###### Open the terminal and type:

```
chmod a+x AndroidTranslationHelper.jar
```


###### Copy in the same directory the CSV and the strings.xml file and execute
```
java -jar AndroidTranslationHelper.jar 2 sheet.csv styles.xml
```


## Example

##### The little story
Let's say you have a translation to do
You open the Translation Editor  you copy and paste that into a spreadsheet and the translation team give your that spreadsheet with like 200 new translations.

You are lazy to copy and paste all that line inside the Strings.xml you look on internet hoping somebody already got the same problem and solve it.

You just need to convert the Excel file to CSV and this script will update your strings.xml

I hope that little script is the answer to that.


##### Action

Clone the repo open terminal and do that
```
java -jar AndroidTranslationHelper.jar 2 sample/sheet.csv sample/strings.xml
```

First argument is the column that will be used as a source
Second argument is the source of the translation the csv file your translation team worked on
Third argument is the strings.xml file that will be updated

If you open strings.xml you will see that the translation are not in French
Now let's try

```
java -jar AndroidTranslationHelper.jar 3 sample/sheet.csv sample/strings.xml
```


Now the translation are in German




###### And that's it
