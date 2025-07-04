
# 🧪 IFGLife_TestCode

## 📌 Deskripsi Project

Project ini merupakan bagian dari proses seleksi teknikal di IFG Life.  
Pengujian dilakukan menggunakan **Katalon Studio**, dengan 2 komponen utama:

1. **Pengujian RESTful API** — menggunakan [reqres.in](https://reqres.in) sebagai dummy API, dengan Katalon sebagai **Producer** dan **Consumer**.
2. **Pengujian Apache Kafka** — menggunakan Kafka lokal, dengan Katalon sebagai Kafka **Consumer**.

> ⚠️ Karena API yang digunakan adalah **dummy API**, maka project **tidak menggunakan database**, karena tidak ada backend sebenarnya.

---

## 🗂️ Struktur Project

```
IFGLife_TestCode/
├── Test Cases/
│   ├── Kafka/
│   │   ├── KafkaConsumeUser
│   │   └── KafkaConsume_InvalidTopic
│   └── REST/
│       ├── PositiveCase/
│       │   ├── VerifyCreateUser
│       │   ├── VerifyGetUsers
│       │   ├── VerifyUpdateUser
│       │   └── VerifyDeleteUser
│       └── NegativeCase/
│           └── VerifyGetUserInvalidID
│
├── Object Repository/
│   └── API/
│       ├── Positive Case/
│       │   ├── CreateUser
│       │   ├── GETUsers
│       │   ├── UpdateUser
│       │   └── DeleteUser
│       └── Negative Case/
│           └── GetUsersInvalidID
│
├── Evidence/
│   ├── 1. Test Result - VerifyCreateUser.png
│   ├── 2. Test Result - VerifyGetUsers.png
│   ├── 3. Test Result - VerifyUpdateUser.png
│   ├── 4. Test Result - VerifyDeleteUser.png
│   ├── 5. Test Result - VerifyGetInvalidID.png
│   ├── 6. Test Result - KafkaConsumeUser.png
│   └── 7. Test Result - KafkaConsume_InvalidTopic.png
```

---

## ✅ Daftar Test Case

### 🔹 REST API (reqres.in)

| Test Case                     | Deskripsi                                               |
|------------------------------|----------------------------------------------------------|
| `VerifyCreateUser`           | Kirim data user baru (POST), verifikasi respons          |
| `VerifyGetUsers`             | Ambil list user (GET), verifikasi data lengkap           |
| `VerifyUpdateUser`           | Ubah user (PUT), verifikasi status code & field berubah  |
| `VerifyDeleteUser`           | Hapus user (DELETE), verifikasi status code 204          |
| `VerifyGetUserInvalidID`     | GET user dengan ID string (`abc`), validasi status 401   |

---

### 🔹 Kafka Test (Katalon sebagai Consumer)

| Test Case                     | Deskripsi                                                     |
|------------------------------|----------------------------------------------------------------|
| `KafkaConsumeUser`           | Menerima data valid JSON dari Kafka topic `test-topic`         |
| `KafkaConsume_InvalidTopic`  | Subscribe ke topic Kafka `test-invalid-topic` (tidak ada data) |

---

## 🧪 Cara Menjalankan Kafka Lokal

> Pastikan Apache Kafka dan ZooKeeper sudah di-download & diekstrak (contoh: `kafka_2.13-3.3.2`)

### 1. Jalankan ZooKeeper
```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

### 2. Jalankan Kafka Broker
```bash
bin/kafka-server-start.sh config/server.properties
```

### 3. Buat Kafka Topic
```bash
bin/kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092
```

### 4. Kirim Data ke Topic (manual)
```bash
bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092
```

Contoh pesan:
```json
{"email": "baiq@ifg.test", "first_name": "Baiq", "last_name": "Azmi"}
```

---

## 📷 Evidence Hasil Test

Semua hasil test (manual + otomatis) disimpan di folder `Evidence/`.

| Test Case                     | Screenshot                                     |
|------------------------------|------------------------------------------------|
| `VerifyCreateUser`           | ✅ `1. Test Result - VerifyCreateUser.png`     |
| `VerifyGetUsers`             | ✅ `2. Test Result - VerifyGetUsers.png`       |
| `VerifyUpdateUser`           | ✅ `3. Test Result - VerifyUpdateUser.png`     |
| `VerifyDeleteUser`           | ✅ `4. Test Result - VerifyDeleteUser.png`     |
| `VerifyGetUserInvalidID`     | ✅ `5. Test Result - VerifyGetInvalidID.png`   |
| `KafkaConsumeUser`           | ✅ `6. Test Result - KafkaConsumeUser.png`     |
| `KafkaConsume_InvalidTopic`  | ✅ `7. Test Result - KafkaConsume_InvalidTopic.png` |

---

## 🔧 Teknologi yang Digunakan

| Komponen        | Tools                             |
|-----------------|-----------------------------------|
| Test Automation | [Katalon Studio](https://katalon.com) |
| API Dummy       | [reqres.in](https://reqres.in)    |
| Kafka           | Apache Kafka 2.13-3.3.2 (local)        |
| Evidence        | Screenshot test Katalon           |

---

## 📌 Catatan Tambahan

- Kafka client (JAR) ditambahkan ke folder `Drivers/` pada project Katalon.
- Karena API yang digunakan adalah dummy, maka tidak dilakukan integrasi dengan database.
- Semua test case ditulis secara modular dan dapat dijalankan satu per satu melalui Katalon Studio.
- Jika Kafka topic salah, test akan tetap lewat karena sesuai dengan ekspektasi negative test.

---

