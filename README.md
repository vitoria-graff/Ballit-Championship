Projeto realizado para o processo seletivo do IT Academy.

Eu desenvolvi a aplicação em Java Swing. A tela inicial, que é a classe TelaInicial, é bem 
simples e funcional. Ela tem um título grande no topo e um botão "Iniciar" que, quando 
clicado, leva o usuário para a tela de cadastro dos times. Eu usei o GridBagLayout para 
organizar tudo, para que o layout fique centralizado. Na parte de cadastro dos times, 
que é feita pela classe TelaCadastroTimes, o usuário pode inserir o nome do time, o grito 
de guerra, e o ano de fundação. Eu implementei várias validações para garantir que os 
dados sejam únicos e estejam dentro dos limites permitidos. Quando tudo está pronto, 
o usuário pode iniciar o campeonato, o que leva à tela de gerenciamento. A 
TelaGerenciamentoCampeonato é onde o campeonato realmente acontece. Ela exibe as 
partidas e permite aplicar penalidades aos times. Eu configurei tudo usando 
BorderLayout e FlowLayout para manter a interface organizada. Também fiz uma lógica 
para avançar as fases do campeonato e determinar um vencedor quando só resta um 
time. Para as partidas em si, tem a TelaAdministracaoPartidas. Nela, o usuário pode 
controlar e monitorar o progresso de uma partida específica. Adicionei os botões “blot” 
e “plif” para atualizar a pontuação de cada time, e também um temporizador que conta 
o tempo restante da partida. Tudo isso foi organizado com GridBagLayout. Também tem 
um sistema de desempate chamado "Grusht", gerenciado pela TelaGrusht. Essa tela tem 
uma barra de progresso que simula a intensidade do grito de guerra dos times, e um 
temporizador que conta regressivamente. No final, a tela determina o vencedor do 
desempate e volta para a tela de gerenciamento. Por fim, a TelaTabela exibe a 
classificação dos times e permite exportar esses dados para um arquivo CSV. Usei uma 
JTextArea para exibir as informações e adicionei um botão de exportação. 
