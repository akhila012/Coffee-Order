<?php
require 'PHPMailer/PHPMailerAutoload.php';
$bodyContent=$_POST['msg'];
$email=$_POST['email_id'];
$mail = new PHPMailer;

$mail->isSMTP();                            // Set mailer to use SMTP
$mail->Host = 'smtp.gmail.com';             // Specify main and backup SMTP servers
$mail->SMTPAuth = true;                     // Enable SMTP authentication
$mail->Username = 'akhila.msit.it@gmail.com'; // SMTP username
$mail->Password = 'password';                 // SMTP password
$mail->SMTPSecure = 'tls';                  // Enable TLS encryption, `ssl` also accepted
$mail->Port = 587;                          // TCP port to connect to

$mail->setFrom('akhila.msit.it@gmail.com', 'Akhila Budaraju');
$mail->addAddress($email_id);   // Add a recipient

$mail->isHTML(true);  // Set email format to HTML
$mail->Subject = 'your order summary';
$mail->Body    =$bodyContent;

if(!$mail->send()) {
    echo 'Message could not be sent.';
    echo 'Mailer Error: ' . $mail->ErrorInfo;
} else {
    echo 'Message has been sent';
}
?>