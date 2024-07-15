padrões de Branch 📜

### Caso não possua o projeto na máquina
1.
```bash
git clone https://github.com/unifor-team/university-api.git
```

2. Após concluir a clonagem do projeto, irão utilizar o comando abaixo para criar uma "nova" ramificação do projeto principal.
```bash
git checkout origin/main
```

3. Após criar a ramificação, nomeie sua branch no qual os tipos podem ser:
- feat
- fix
- doc
- test

```bash
git checkout -b [tipo]/[nome]
```

4. Na hora de 'commitar' os arquivos, deve-se seguir um padrão
```bash
git commit -m "[tipo]: [nome]"
```

5. Ao finalizar os commits, deve subir a aplicação utilizando o comando abaixo
```bash
git push -u origin [nome da branch]
```

Lembre-se de criar o PULL REQUEST para que possa ser feita a revisão

### Caso possua o projeto na máquina

Diferentemente do processo anterior, deverá ser utilizando inicialmente para atualizar seu local com a ultima versão adicionada das branchs

```bash
git fetch
```

```bash
git pull origin master
```

Após isso, basta seguir com o processo como se ja tivesse feito o clone do projeto a partir do processo nª 2.
