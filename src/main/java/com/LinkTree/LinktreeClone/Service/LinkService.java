package com.LinkTree.LinktreeClone.Service;


import com.LinkTree.LinktreeClone.Model.Link;
import com.LinkTree.LinktreeClone.Repository.LinkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;


    public List<Link> getAllData() {
        List<Link> links = linkRepository.findAll();
        return links;
    }

    public Link saveLinksData(Link link) {

        Link saveLinkData = linkRepository.save(link);
        return saveLinkData;
    }

    public Optional<Link> deleteLinkData(Long id) {

        Optional<Link> linksData = linkRepository.findById(id);
        linkRepository.deleteById(id);
        return linksData;
    }

    public Link updateLinkData(Link loads , Long id ) {

        try {
            Link linkDataWithId = linkRepository.findById(id).orElseThrow();

            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(loads);
            Link linksData = mapper.readValue(jsonString, Link.class);
            linksData.setId(id);
            Link saveLinksData = linkRepository.save(linksData);
            return saveLinksData;

        } catch (Exception e) {
            System.out.println("linkData not found in database " + e);
        }
        return null;
    }

    public Optional<Link> getDataById(Long id) {

        Optional<Link> linksData = linkRepository.findById(id);
        return linksData;
    }

    public List<Link> getLinksByUserId(Long userId){

        return linkRepository.getAllLinksByUserId(userId);
    }


    public void deleteDataByUserId(Long id) {

        linkRepository.deleteLinkDataByUserId(id);
    }
}
