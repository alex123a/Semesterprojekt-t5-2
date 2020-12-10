# Plastic Roads

Spillet der handler om at samle plastik og omdanne det til en ny vej.

<u>Lavet af gruppe T5-2</u>
Janik Sunke, Frederik Nielsen, Alex Valentiner, Valdemar Birk og Nikolaj Jensen.

Opsætning af spillet:

1. Åbn mappen GUIbaseret, i en IDEA. (IntelliJ)
2. Vælg File og derefter Project Structure...

<img src="C:\Users\janik\AppData\Roaming\Typora\typora-user-images\image-20201210101618039.png" alt="image-20201210101618039" style="zoom: 33%;" />

3. Vælg indstillingen Libraries, tryk derefter på + og vælg Java.

<img src="C:\Users\janik\AppData\Roaming\Typora\typora-user-images\image-20201210100554559.png" alt="image-20201210100554559" style="zoom:50%;" />

4. Find placeringen for din Javafx, og marker mappen lib.

<img src="C:\Users\janik\AppData\Roaming\Typora\typora-user-images\image-20201210100723461.png" alt="image-20201210100723461" style="zoom:67%;" />

5. Kopier hele placeringen for filen, ved at markere linjen som nedenfor. Derefter tryk derefter OK, og OK igen.

![image-20201210101706962](C:\Users\janik\AppData\Roaming\Typora\typora-user-images\image-20201210101706962.png)

6. Tryk Apply, og derefter OK. Nu burde den ikke skrive nogle fejl i din IDEA (IntelliJ)
7. For at køre spillet skal vi op og ændre i Run, og derefter Edit Configurations...

<img src="C:\Users\janik\AppData\Roaming\Typora\typora-user-images\image-20201210101205074.png" alt="image-20201210101205074" style="zoom:67%;" />

8. Her erstatter du den placering der står inden for "" med det du tidligere kopierede.

    --module-path "Indsæt her" --add-modules javafx.controls,javafx.fxml

9. Tryk Apply og OK. Derefter vil det være muligt at starte spillet fra din IDEA.