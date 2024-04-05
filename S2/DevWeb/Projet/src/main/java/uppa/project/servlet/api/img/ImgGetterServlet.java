package uppa.project.servlet.api.img;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "imgGetterServlet", value = "/api/imgGet")
public class ImgGetterServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Récupérer le nom de l'image à partir de la requête
    String imgName = request.getParameter("imgName");

    // Vérifier si le nom de l'image est fourni
    if (imgName != null && !imgName.isEmpty()) {
      // Récupérer le chemin complet de l'image (remplacez "path_to_your_images_folder" par le chemin réel)
      String imagePath = request.getRequestDispatcher("/img/" + imgName).toString();

      // Ouvrir un flux d'entrée vers le fichier image
      try (InputStream inputStream = getServletContext().getResourceAsStream(imagePath)) {
        if (inputStream != null) {
          // Récupérer le flux de sortie de la réponse HTTP
          OutputStream outputStream = response.getOutputStream();

          // Lire les données de l'image et écrire dans le flux de sortie de la réponse
          byte[] buffer = new byte[1024];
          int bytesRead;
          while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
          }

          // Flusher le flux de sortie
          outputStream.flush();
        } else {
          // Si l'image n'est pas trouvée, retourner une réponse 404 (non trouvé)
          response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
      }
    } else {
      // Si le nom de l'image n'est pas fourni, retourner une réponse 400 (mauvaise requête)
      response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}
