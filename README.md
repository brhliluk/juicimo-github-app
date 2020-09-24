# juicimo-github-app

Tvým úkolem je vytvořit seznam-detail aplikaci, která bude načítat a zobrazovat seznam issues z GitHubu a ukládat je do databáze v telefonu. Pro komunikaci s GitHubem použij jejich v3 (REST) API, nikoli v4 (GraphQL) API. Dokumentaci API najdeš tady: 
https://developer.github.com/v3/. Pro databázi použij lokální databázi v telefonu (typicky SQLite). 
Když aplikaci otevřu, tak by měla načíst seznam Git veřejných repozitářů GitHub uživatele Inza (https://github.com/Inza). Ten by měla zobrazit jako seznam. Když tapnu na libovolný z repozitářů, tak by aplikace měla zobrazit detail tohoto repozitáře. A v detailu repozitáře by měl být vidět seznam 10 posledních commitů, jsou-li k dispozici (použij endoint https://developer.github.com/v3/repos/commits/#list-commits-on-a-repository). 
Repozitáře a commity ukládej do lokální databáze v telefonu. Aplikace by měla fungovat tak, že vždy, když ji otevřeš, tak načteš z API seznam repozitářů a ty uložíš do databáze. Data v databázi vždy promazávej (nemusíš tak řešit rozdíly - to by bylo moc složité). Když otevřeš aplikaci bez internetu, načti data z databáze (zobrazí se tedy jen pokud tam jsou). A poté když otevřeš detail repozitáře, tak načti z API seznam commitů a ulož je do databáze. Jestli chceš implementovat věci jako pull to refresh pro znovunačtení dat z API (v seznamu nebo v detailu) je na tobě. 
Aplikace by dále měla obsahovat nějakou “About App” stránku, kde můžeš mít své jméno, atd. 
Jak přesně bude aplikace vypadat a jestli bude nějak extra vymazlená už je na tobě ;). Zkus nás překvapit. 
Měl/a by ses snažit, aby kód aplikace nebyl špageta kód, abys např. neměl/a volání HTTP requestů v aktivitě, ale v nějaké třídě, kterou budeš reprezentovat model či servisní třídy. 
Necháme to na tobě, zkus vytvořit takový kód, na který bys sám byl hrdý/á. Pokud budeš potřebovat i nějaké další knihovny, tak další výběr už necháme na tobě. 
Aplikaci během vývoje commituj a pushuj do Git repozitáře (zajímá nás i jak pracuješ s Gitem). Ten si vytvoř na GitHubu - může být veřejný, nebo neveřejný - to už je na tobě. Jen nám ho pak musíš nasdílet. 
Teorie praví, že by ti vytvoření úkolu nemělo zabrat příliš dlouho, ale je to zcela na tobě, jen je třeba, abys dodržel/a termín, na kterém jsme se domluvili.
