# spring-ssl

STEP 1: Download file certificate.crt atas url tujuan melalui browser
STEP 2: run command di bawah untuk mendaftarkan certificate ke java cacerts, jika di LINUX hilangkan tanda petik (")
```
keytool -import -alias mpng2bulk -file "C:/Users/Made.Yasa/Documents/GitHub/mpng2bulk-ubp/src/main/resources/mpng2bulk.crt" -keystore "C:/Program Files/Java/jdk1.8.0_202/jre/lib/security/cacerts" -storepass changeit
```
STEP 3 : cek apakah sudah teregister atau belum, jika di LINUX hilangkan tanda petik (")
```
keytool -list -keystore "C:\Program Files\Java\jdk1.8.0_202\jre\lib\security\cacerts" -storepass changeit
```
STEP DONE

STEP delete (optional) : run command di bawah jika ingin menghapus certificate dari cacerts, jika di LINUX hilangkan tanda petik (")
```
keytool -delete -alias mpng2bulk -keystore "C:\Program Files\Java\jdk1.8.0_202\jre\lib\security\cacerts" -storepass changeit
```
