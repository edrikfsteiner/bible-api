# Bible API

Este é um serviço web desenvolvido em Java com Spring que permite buscar versículos bíblicos e passagens através de uma API REST. O projeto integra-se com a [Bible API](https://bible-api.com/) para obter os dados das escrituras e oferece funcionalidades para consultar versículos únicos, intervalos de versículos, múltiplos intervalos e diferentes traduções.

## Requisitos

- Java 17
- Maven
- Docker

## Configuração e Execução

### Executar localmente

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/edrikfsteiner/bible-api.git
   cd bible-api
   ```

2. **Compile e rode o projeto:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. **Acesse a API:**
   O serviço estará disponível em `http://localhost:8080/api`.

### Executar com Docker

1. **Build da imagem Docker:**
   ```bash
   docker build -t bible-api .
   ```

2. **Rodar o container:**
   ```bash
   docker-compose up
   ```

3. **Acesse a API:**
   O serviço estará disponível em `http://localhost:8080/api`.

## Rotas Disponíveis

### Rota `/sobre`

- **Método:** `GET`
- **Descrição:** Retorna informações sobre o estudante e o projeto.
  ```json
  {
    "projeto": "bibleAPI",
    "estudante": "Edrik Steiner"
  }
  ```

### Rota `/api/{book}-{chapter}:{verse}`

- **Método:** `GET`
- **Descrição:** Retorna um versículo específico da Bíblia.
- **Requisição:**
  ```
  GET /api/john-3:16
  ```
- **Resposta:**
  ```json
  {
    "reference": "John 3:16",
    "verses": [
      {
        "book_id": "JHN",
        "book_name": "John",
        "chapter": 3,
        "verse": 16,
        "text": "\nFor God so loved the world, that he gave his one and only Son, that whoever believes in him should not perish, but have eternal life.\n\n"
      }
    ],
    "text": "\nFor God so loved the world, that he gave his one and only Son, that whoever believes in him should not perish, but have eternal life.\n\n",
    "translation_id": "web",
    "translation_name": "World English Bible",
    "translation_note": "Public Domain"
  }
  ```

### Rota `/api/{book}+{chapter}:{verseStart}-{verseEnd}`

- **Método:** `GET`
- **Descrição:** Retorna um intervalo de versículos da Bíblia.
- **Requisição:**
  ```
  GET /api/romans+12:1-2
  ```
- **Resposta:**
  ```json
  {
    "reference": "Romans 12:1-2",
    "verses": [
      {
        "book_id": "ROM",
        "book_name": "Romans",
        "chapter": 12,
        "verse": 1,
        "text": "Therefore I urge you, brothers, by the mercies of God, to present your bodies a living sacrifice, holy, acceptable to God, which is your spiritual service.\n"
      },
      {
        "book_id": "ROM",
        "book_name": "Romans",
        "chapter": 12,
        "verse": 2,
        "text": "Don’t be conformed to this world, but be transformed by the renewing of your mind, so that you may prove what is the good, well-pleasing, and perfect will of God.\n"
      }
    ],
    "text": "Therefore I urge you, brothers, by the mercies of God, to present your bodies a living sacrifice, holy, acceptable to God, which is your spiritual service.\nDon’t be conformed to this world, but be transformed by the renewing of your mind, so that you may prove what is the good, well-pleasing, and perfect will of God.\n",
    "translation_id": "web",
    "translation_name": "World English Bible",
    "translation_note": "Public Domain"
  }
  ```

### Rota `/api/verse`

- **Método:** `POST`
- **Descrição:** Recebe um JSON com um versículo específico e retorna o conteúdo correspondente.
- **Requisição:**
  ```json
  {
    "book": "john",
    "chapter": 3,
    "verse": 16
  }
  ```
- **Resposta:**
  ```json
  {
    "reference": "John 3:16",
    "verses": [
      {
        "book_id": "JHN",
        "book_name": "John",
        "chapter": 3,
        "verse": 16,
        "text": "\nFor God so loved the world, that he gave his one and only Son, that whoever believes in him should not perish, but have eternal life.\n\n"
      }
    ],
    "text": "\nFor God so loved the world, that he gave his one and only Son, that whoever believes in him should not perish, but have eternal life.\n\n",
    "translation_id": "web",
    "translation_name": "World English Bible",
    "translation_note": "Public Domain"
  }
  ```

### Rota `/api/verse-range`

- **Método:** `POST`
- **Descrição:** Recebe um JSON com uma faixa de versículos e retorna o conteúdo correspondente.
- **Requisição:**
  ```json
  {
    "book": "john",
    "chapter": 3,
    "verseStart": 15,
    "verseEnd": 16
  }
  ```
- **Resposta:**
  ```json
  {
    "reference": "John 3:15-16",
    "verses": [
      {
        "book_id": "JHN",
        "book_name": "John",
        "chapter": 3,
        "verse": 15,
        "text": "\nthat whoever believes in him should not perish, but have eternal life.\n\n"
      },
      {
        "book_id": "JHN",
        "book_name": "John",
        "chapter": 3,
        "verse": 16,
        "text": "\nFor God so loved the world, that he gave his one and only Son, that whoever believes in him should not perish, but have eternal life.\n\n"
      }
    ],
    "text": "\nthat whoever believes in him should not perish, but have eternal life.\n\n\nFor God so loved the world, that he gave his one and only Son, that whoever believes in him should not perish, but have eternal life.\n\n",
    "translation_id": "web",
    "translation_name": "World English Bible",
    "translation_note": "Public Domain"
  }
  ```

### Rota `/api/multiple-ranges`

- **Método:** `POST`
- **Descrição:** Recebe um JSON com múltiplos intervalos de versículos e retorna o conteúdo correspondente.
- **Requisição:**
  ```json
  {
    "book": "romans",
    "range": "12:1-2,5-7,9,13:1-9&10"
  }
  ```
- **Resposta:**
  ```json
  {
    "reference": "Romans 12:1-2,5-7,9,13:1-9,10",
    "verses": [
      {
        "book_id": "ROM",
        "book_name": "Romans",
        "chapter": 12,
        "verse": 1,
        "text": "Therefore I urge you, brothers, by the mercies of God, to present your bodies a living sacrifice, holy, acceptable to God, which is your spiritual service.\n"
      },
      {
        "book_id": "ROM",
        "book_name": "Romans",
        "chapter": 12,
        "verse": 2,
        "text": "Don’t be conformed to this world, but be transformed by the renewing of your mind, so that you may prove what is the good, well-pleasing, and perfect will of God.\n"
      },
      
      ...

    ],
    "text": "Therefore I urge you, brothers, by the mercies of God, [...]",
    "translation_id": "web",
    "translation_name": "World English Bible",
    "translation_note": "Public Domain"
  }
  ```

### Rota `/api/translation`

- **Método:** `POST`
- **Descrição:** Recebe um JSON com múltiplos intervalos de versículos e a tradução escolhida e retorna o conteúdo correspondente.
- **Requisição:**
  ```json
  {
    "book": "romans",
    "range": "12:1-2,5-7,9,13:1-9&10",
    "translate": "almeida"
  }
  ```
- **Resposta:**
  ```json
  {
    "reference": "Romanos 12:1-2,5-7,9,13:1-9,10",
    "verses": [
      {
        "book_id": "ROM",
        "book_name": "Romanos",
        "chapter": 12,
        "verse": 1,
        "text": "Rogo-vos pois, irmãos, pela compaixão de Deus, que apresenteis os vossos corpos como um sacrifício vivo, santo e agradável a Deus, que é o vosso culto racional."
      },
      {
        "book_id": "ROM",
        "book_name": "Romanos",
        "chapter": 12,
        "verse": 2,
        "text": "E não vos conformeis a este mundo, mas transformai-vos pela renovação da vossa mente, para que experimenteis qual seja a boa, agradável, e perfeita vontade de Deus."
      },

      ...

    ],
    "text": "Rogo-vos pois, irmãos, pela compaixão de Deus, [...]",
    "translation_id": "almeida",
    "translation_name": "João Ferreira de Almeida",
    "translation_note": "Public Domain"
  }
  ```
