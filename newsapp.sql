-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 31, 2023 at 01:43 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `newsapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL,
  `comment_text` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`comment_id`, `comment_text`, `user_id`, `article_id`) VALUES
(3, 'Another Comment about smth without any meaning', 11, 8),
(4, 'this is a lorem ispum ispum', 9, 7),
(55, 'add an cpmmment to russia', 12, 2),
(56, 'heey', 9, 2),
(64, 'test', 12, 2),
(65, 'test', 12, 3),
(66, 'test', 12, 2),
(67, 'test', 12, 4),
(74, 'new Comment', 12, 2),
(84, '123', 12, 4),
(85, 'ww', 12, 4),
(87, 'elon mask', 12, 4),
(88, 'testing here', 12, 7);

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE `likes` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `is_liked` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `likes`
--

INSERT INTO `likes` (`id`, `user_id`, `article_id`, `is_liked`) VALUES
(5, 12, 7, 'yes'),
(6, 12, 7, 'yes'),
(7, 12, 7, 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `category` text NOT NULL,
  `date` date NOT NULL,
  `content` text NOT NULL,
  `src` text NOT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `title`, `category`, `date`, `content`, `src`, `image`) VALUES
(2, 'Russia\'s war in Ukraine,Russia\'s goal to eliminate Ukraine from the map', 'Wars', '2023-07-23', 'Russia has \"already lost the war\" in Ukraine in terms of what Moscow and Russian President Vladimir Putin sought to achieve, US Secretary of State Antony Blinken told CNN.\r\n\r\n“The objective was to erase Ukraine from the map, to eliminate its independence, its sovereignty, to subsume it into Russia. That failed a long time ago,” the secretary said in an exclusive interview that aired Sunday.Blinken acknowledged that Ukraine’s mission to regain territory captured by Moscow — which has gotten off to a slow start, by its own estimation — would be “a very hard fight.” He predicted that the war, which recently surpassed the 500 days mark, would continue for “several months.”\r\n\r\nHowever, he said, along with the aid, military equipment and training Ukraine is receiving from various countries, Kyiv’s cause represents “the decisive element.”', 'CNN', 'https://dynaimage.cdn.cnn.com/cnn/digital-images/org/59a19cc5-b0f5-4fa4-9596-4f1a4e8b42df.jpg'),
(3, 'Biblical proportions’: 3 months’ worth of rainfall floods Nova Scotia,', 'Climate', '2023-07-05', 'Three months’ worth of rain over the course of one day has flooded the Canadian province of Nova Scotia since Friday night, inundating streets, forcing evacuations and leaving at least four people missing – including two children.\r\n\r\nA provincewide state of emergency has been declared, including for the Halifax regional municipality as well as nearby East Hants, West Hants, Lunenburg and Queens.\r\n\r\n“We have had biblical proportions of rain over the night and into the day,” Halifax Mayor Mike Savage said Saturday.\r\n\r\nThe two missing children were passengers in a vehicle that became submerged under water in West Hants, Premier Tim Houston said in a press conference Saturday. Three others who were in the car with them were able to escape, he said.\r\n\r\nMallory and Spenser at their home in Waterbury, Vermont.\r\nThey planned to get married outside their beloved home. And then the floods came\r\nIn a separate incident, a young person and a man were also reported missing in the West Hants area after their vehicle was submerged, while two other passengers traveling in the same vehicle were rescued, Houston said.\r\n\r\nOfficials have not released the names or ages of the four as the search for them continues.', 'CNN', 'https://media.cnn.com/api/v1/images/stellar/prod/230722223438-01-canada-rain-floods.jpg?c=16x9&q=h_720,w_1280,c_fill/f_webp'),
(4, 'Elon Musk says Twitter to change logo, will bid adieu to ‘all the birds’', 'Technology', '2023-07-09', 'Elon Musk said he was looking to change Twitter’s logo, tweeting: “And soon we shall bid adieu to the twitter brand and, gradually, all the birds.”\r\n\r\nIn a tweet at 12:06 a.m. ET (0406 GMT) on Sunday, the billionaire owner added: “If a good enough X logo is posted\r\ntonight, we’ll make go live worldwide tomorrow.”\r\n\r\nMusk posted an image of a flickering “X” but did not givefurther details. The company did not immediately respond to a\r\nrequest for comment.\r\n\r\nUnder Musk’s tumultuous tenure since buying Twitter in October, the company changed its business name to X Corp, reflecting the billionaire’s vision to create a “super app,” like China’s WeChat.\r\n\r\nTwitter’s website says its logo, depicting a blue bird, is “our most recognizable asset,” adding “That’s why we’re so\r\nprotective of it.”\r\n\r\nThe bird was temporarily replaced in April by Dogecoin’s Shiba Inu dog, which ended up helping add as much as $4 billion to the meme coin’s market value.\r\n\r\nTwitter’s most recent complication was a lawsuit being filed on Tuesday claiming the firm owes at least $500 million in severance pay to former employees.\r\n\r\nMusk’s company has laid off more than half its workforce to cut costs since he bought the company.', 'ALARABIYA NEWS', 'https://vid.alarabiya.net/images/2023/04/12/93b3cf20-4ea9-445c-9ed3-e59664d0ab70/93b3cf20-4ea9-445c-9ed3-e59664d0ab70_16x9_1200x676.JPG?width=1120&format=jpg'),
(5, 'Who are the riders to watch in the 2023 Women\'s Tour de France?', 'Sports', '2023-07-17', 'As the Women\'s Tour de France rolls out of the French city of Clermont-Ferrand on Sunday, the Netherlands\' Annemiek van Vleuten, who has already won this year\'s Giro d’Italia and Vuelta cycling races, is back to defend her title. However, a host of contenders are hoping to unseat her before she retires at the end of this year. FRANCE 24 takes a look at van Vleuten and her main challengers. \r\nThe Netherlands\' van Vleuten won the first edition of the Women’s Tour de France at the age of 39 last year despite having a stomach bug during the first half of the race that nearly forced her to withdraw. She hopes to retain her title and keep the rainbow jersey worn by the reigning Tour de France champion before retiring at the end of this year. In 2022, she won the world’s three major cycling races: Spain\'s Vuelta, the Giro d’Italia and the Tour de France, and rounded off the year by winning the women’s world championship road race in Australia.  ', 'France24', 'https://s.france24.com/media/display/37b46b4a-2968-11ee-b314-005056bf30b7/w:980/p:16x9/000_33A226W.webp'),
(6, 'Canada tests the world\'s first hydrogen-powered passenger train', 'Renewable Energy', '2023-07-16', 'The world\'s first hydrogen-powered passenger train, Coradia iLint, is currently being tested in Quebec\'s Charlevoix region. Designed in France by rolling stock manufacturer Alstom, the zero-emissions train runs on electricity produced by mixing hydrogen with oxygen, meaning that moisture its only waste product. Alstom said Europe has already placed an order for 41 hydrogen trains.', 'France24', 'https://i.cbc.ca/1.6890391.1687892526!/fileImage/httpImage/image.jpg_gen/derivatives/16x9_780/coradia-ilint.jpg'),
(7, 'Spanish voters face stark left-right divide in snap election', 'Politics', '2023-07-25', 'More than a million first-time voters to cast ballots\r\nA estimated 1.6 million young people will vote today for the first time.\r\nAccording to a June poll carried out by the National Research Centre (CIS) - which has faced a good deal of criticism for allegedly exaggerating support for the left in its studies - the Socialists (PSOE) are the leading party among 18-24 year olds, followed by the People’s Party (PP), Sumar and Vox, respectively.', 'BBC', 'https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/vivo/live/images/2023/7/23/aa821484-6994-4ee8-981f-88d58e656f77.jpg'),
(8, 'July likely to be world’s warmest month on record, says NASA scientist', 'Climate', '2023-07-24', 'This month has already seen daily records shattered according to tools run by the European Union and the University of Maine, which combine ground and satellite data into models to generate preliminary estimates.\r\n\r\nThough they differ slightly from one another, the trend of extreme heat is unmistakable and will likely be reflected in the more robust monthly reports issued later by US agencies, said Schmidt in a NASA briefing with reporters.\r\n\r\n\"We are seeing unprecedented changes all over the world -- the heat waves that we\'re seeing in the US in Europe and in China are demolishing records, left, right and center,\" he added.\r\n\r\nWhat\'s more, the effects cannot be attributed solely to the El Nino weather pattern, which \"has really only just emerged.\"\r\n\r\nThough El Nino is playing a small role, \"what we\'re seeing is the overall warmth, pretty much everywhere, particularly in the oceans. We\'ve been seeing record-breaking sea surface temperatures, even outside of the tropics, for many months now.\r\n\r\n\"And we will anticipate that is going to continue, and the reason why we think that\'s going to continue, is because we continue to put greenhouse gasses into the atmosphere.\"\r\n\r\nWhat is happening right now is increasing the chances that 2023 will be the hottest year on record, which Schmidt currently assigned a \"50-50 chance\" based on his calculations, though he said other scientists had placed it as high as 80 percent.', 'France24', 'https://s.france24.com/media/display/af24938c-0d14-11ee-925b-005056a90284/w:980/p:16x9/000_33J93TB.webp'),
(9, 'Il mondo di Kissinger e Prodi non c’è più. Meglio superarlo', 'Politics', '2023-07-02', 'Cinquantadue anni dopo lo storico viaggio del 1971, Henry Kissinger torna in Cina alla veneranda età di 100 anni, compiendo così l’ultimo straordinario gesto della sua lunga e feconda vita. Lo riceve anche il presidente Xi Jinping (che nel 1971 aveva 18 anni), a testimonianza della forte capacità della Cina di mantenere vivo il senso della storia, specialità in cui noi occidentali abbiamo molto da imparare dalle altre civiltà. Kissinger torna a Pechino e ribadisce la necessità di un forte rapporto USA-Cina, anche come elemento essenziale per gli equilibri pacifici del mondo.\r\n\r\nPochi giorni dopo Romano Prodi è intervenuto anche sui grandi temi internazionali, con un duro rimprovero all’Europa debole e divisa, relegata sostanzialmente a una posizione di vassallaggio nei confronti dell’alleato americano. Non c’è motivo di perdere troppo tempo a ribadire con quanto rispetto vanno ascoltate le parole di chi ha una grande esperienza internazionale, quindi Kissinger e Prodi sono voci nobili e importanti. Va però anche detto con una certa franchezza che parlano considerando molto più il mondo che hanno conosciuto direttamente rispetto a quello attuale, sconvolto invece da potenti e brutali mutazioni di cui non si può non tener conto.\r\n\r\nLa Cina visitata da Kissinger nel 1971 era un paese arretrato afflitto da enormi problemi di sviluppo, incapace di reggere il confronto con gli USA se non sul tavolo da ping-pong. L’apertura e il dialogo dell’epoca non hanno quindi nulla a che fare con gli equilibri attuali, che vedono il governo di Pechino già a capo del secondo bilancio mondiale di spesa militare e ora capace di una presenza determinante nei cinque continenti su tutti i dossier più importanti, dalle attività finanziarie al real estate, dalle miniere alle produzioni agricole, dall’intelligence alle strutture culturali.', 'Italy24', 'https://it.italy24.press/temp/resized/medium_2023-07-23-4713cfa043.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `img_url` text NOT NULL,
  `admin` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `img_url`, `admin`) VALUES
(9, 'Sam', '111', '111', 'https://e0.pxfuel.com/wallpapers/39/255/desktop-wallpaper-stylish-boys-cool-profile-pics-dp-for-facebook-whatsapp-2019-boy-smoke.jpg', 'no'),
(11, 'gogo', 'gogo', '123', '', 'no'),
(12, 'smith', 'smith', '123', 'https://w0.peakpx.com/wallpaper/979/89/HD-wallpaper-purple-smile-design-eye-smily-profile-pic-face.jpg', 'no'),
(13, 'admin', 'admin@gmail.com', 'admin', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzHQv_th9wq3ivQ1CVk7UZRxhbPq64oQrg5Q&usqp=CAU', 'yes'),
(16, 'user10', 'hhhh', '123', '', 'no'),
(25, 'admin2', 'admin', 'admin', '', 'yes');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `post_id` (`article_id`) USING BTREE;

--
-- Indexes for table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `article_id` (`article_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT for table `likes`
--
ALTER TABLE `likes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `posts` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
