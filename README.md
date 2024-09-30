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
  ```
  Reference: John 3:16

  John 3:16 - 
  For God so loved the world, that he gave his one and only Son, that whoever believes in him should not perish, but have eternal life.



  Full Text:

  For God so loved the world, that he gave his one and only Son, that whoever believes in him should not perish, but have eternal life.
  ```

### Rota `/api/{book}+{chapter}:{verseStart}-{verseEnd}`

- **Método:** `GET`
- **Descrição:** Retorna um intervalo de versículos da Bíblia.
- **Requisição:**
  ```
  GET /api/romans+12:1-2
  ```
- **Resposta:**
  ```
  Reference: Romans 12:1-2

  Romans 12:1 - Therefore I urge you, brothers, by the mercies of God, to present your bodies a living sacrifice, holy, acceptable to God, which is your spiritual service.

  Romans 12:2 - Don’t be conformed to this world, but be transformed by the renewing of your mind, so that you may prove what is the good, well-pleasing, and perfect will of God.


  Full Text:
  Therefore I urge you, brothers, by the mercies of God, to present your bodies a living sacrifice, holy, acceptable to God, which is your spiritual service.
  Don’t be conformed to this world, but be transformed by the renewing of your mind, so that you may prove what is the good, well-pleasing, and perfect will of God.
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
  ```
  Reference: John 3:16

  John 3:16 - 
  For God so loved the world, that he gave his one and only Son, that whoever believes in him should not perish, but have eternal life.



  Full Text:

  For God so loved the world, that he gave his one and only Son, that whoever believes in him should not perish, but have eternal life.
  ```

### Rota `/api/verse-range`

- **Método:** `POST`
- **Descrição:** Recebe um JSON com uma faixa de versículos e retorna o conteúdo correspondente.
- **Requisição:**
  ```json
  {
    "book": "romans",
    "chapter": 12,
    "verseStart": 1,
    "verseEnd": 2
  }
  ```
- **Resposta:**
  ```
  Reference: Romans 12:1-2

  Romans 12:1 - Therefore I urge you, brothers, by the mercies of God, to present your bodies a living sacrifice, holy, acceptable to God, which is your spiritual service.

  Romans 12:2 - Don’t be conformed to this world, but be transformed by the renewing of your mind, so that you may prove what is the good, well-pleasing, and perfect will of God.


  Full Text:
  Therefore I urge you, brothers, by the mercies of God, to present your bodies a living sacrifice, holy, acceptable to God, which is your spiritual service.
  Don’t be conformed to this world, but be transformed by the renewing of your mind, so that you may prove what is the good, well-pleasing, and perfect will of God.
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
  ```
  Reference: Romans 12:1-2,5-7,9,13:1-9,10

  Romans 12:1 - Therefore I urge you, brothers, by the mercies of God, to present your bodies a living sacrifice, holy, acceptable to God, which is your spiritual service.

  Romans 12:2 - Don’t be conformed to this world, but be transformed by the renewing of your mind, so that you may prove what is the good, well-pleasing, and perfect will of God.

  [...]

  Full Text:
  Therefore I urge you, brothers, by the mercies of God, [...]
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
  ```
  Reference: Romanos 12:1-2,5-7,9,13:1-9,10

  Romanos 12:1 - Rogo-vos pois, irmãos, pela compaixão de Deus, que apresenteis os vossos corpos como um sacrifício vivo, santo e agradável a Deus, que é o vosso culto racional.   
  Romanos 12:2 - E não vos conformeis a este mundo, mas transformai-vos pela renovação da vossa mente, para que experimenteis qual seja a boa, agradável, e perfeita vontade de Deus.  

  [...]

  Full Text:
  Rogo-vos pois, irmãos, pela compaixão de Deus, [...]
  ```
